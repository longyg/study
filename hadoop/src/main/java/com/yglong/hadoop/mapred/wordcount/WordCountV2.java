package com.yglong.hadoop.mapred.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * 单词出现次数统计
 * 提供两个高级功能：
 *  - 可以指定一个配置文件，忽略指定的字符/文本等
 *  - 可以设置是否忽略字母大小写
 *
 * 使用了Mapper类的setup方法，获取上述两个配置信息
 *
 * 注意：该程序不能在本地运行，只能在集群中运行，因为使用Cache功能，需要用到HDFS
 */
public class WordCountV2 {
    public static final String CASE_SENSITIVE_PROP = "wordcount.case.sensitive";
    public static final String SKIP_PATTERNS_PROP = "wordcount.skip.patterns";
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        enum CounterEnum {
            INPUT_WORDS;
        }

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        private boolean caseSensitive;
        private Set<String> patternToSkip = new HashSet<>();

        private Configuration conf;
        private BufferedReader reader;

        @Override
        protected void setup(Context context) throws IOException {
            conf = context.getConfiguration();
            caseSensitive = conf.getBoolean(CASE_SENSITIVE_PROP, true);
            if (conf.getBoolean(SKIP_PATTERNS_PROP, false)) {
                URI[] patternUris = Job.getInstance(conf).getCacheFiles();
                for (URI uri : patternUris) {
                    Path patternsPath = new Path(uri.getPath());
                    String patternsFileName = patternsPath.getName().toString();
                    parseSkipFile(patternsFileName);
                }
            }
        }

        private void parseSkipFile(String filename) {
            try {
                reader = new BufferedReader(new FileReader(filename));
                String pattern = null;
                while ((pattern = reader.readLine()) != null) {
                    patternToSkip.add(pattern);
                }
            } catch (Exception e) {
                System.err.println("Exception while parsing the cached file: " + filename +
                        " : " + StringUtils.stringifyException(e));
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException {
            if (null != reader) {
                reader.close();
            }
        }

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String line = (caseSensitive) ? value.toString() : value.toString().toLowerCase();
            for (String pattern : patternToSkip) {
                line = line.replaceAll(pattern, "");
            }
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreElements()) {
                word.set(tokenizer.nextToken());
                context.write(word, one);
                Counter counter = context.getCounter(CounterEnum.class.getName(), CounterEnum.INPUT_WORDS.toString());
                counter.increment(1);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] remainingArgs = parser.getRemainingArgs();
        if (remainingArgs.length != 2 && remainingArgs.length != 4 && remainingArgs.length != 5) {
            System.err.println("Usage: WordCountV2 <in> <out> [-skip skipPatternFile] [jar_path]");
            System.exit(2);
        }

        Job job = Job.getInstance(conf, "word count v2");
        job.setJarByClass(WordCountV2.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        List<String> otherArgs = new ArrayList<>();
        for (int i = 0; i < remainingArgs.length; i++) {
            if ("-skip".equals(remainingArgs[i])) {
                job.addCacheFile(new Path(remainingArgs[++i]).toUri());
                job.getConfiguration().setBoolean(SKIP_PATTERNS_PROP, true);
            } else {
                otherArgs.add(remainingArgs[i]);
            }
        }
        FileInputFormat.addInputPath(job, new Path(otherArgs.get(0)));

        Path out = new Path(otherArgs.get(1));
        if (out.getFileSystem(conf).exists(out)) {
            out.getFileSystem(conf).delete(out, true);
        }

        if (null != otherArgs.get(2)) {
            job.setJar(otherArgs.get(2));
        }
        FileOutputFormat.setOutputPath(job, out);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
