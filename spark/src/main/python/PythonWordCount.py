from pyspark import *

if __name__ == '__main__':
    conf = SparkConf().setAppName("PythonWordCount").setMaster("local[*]")
    sc = SparkContext(conf=conf)
    sc.textFile("C:/ylong/workspace/bigdata/study/spark/data/wc/input")\
        .flatMap(lambda line: line.split(' '))\
        .map(lambda w: (w, 1))\
        .reduceByKey(lambda x, y: x + y)\
        .sortBy(lambda t: t[1], False)\
        .saveAsTextFile("C:/ylong/workspace/bigdata/study/spark/data/wc/output3")
    sc.stop()