import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class JavaWordCount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("JavaWordCount").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<String> lines = jsc.textFile(args[0]);
        lines.flatMap(line -> Arrays.stream(line.split(" ")).iterator())
                .mapToPair(w -> Tuple2.apply(w, 1))
                .reduceByKey((x, y) -> x + y)
                .mapToPair(t -> t.swap())
                .sortByKey(false)
                .mapToPair(t -> t.swap())
                .saveAsTextFile(args[1]);
        jsc.stop();
    }
}
