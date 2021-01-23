package com.yglong.spark.scala.student

import org.apache.spark.sql.SparkSession

case class Stu(
  clazz: String,
  name: String,
  age: Int,
  gender: String,
  major: String,
  score: Int
)

object StudentDf {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("DataFrame")
      .master("local[*]")
      .getOrCreate()

    val path = "C:\\ylong\\workspace\\bigdata\\study\\spark\\data\\student\\student.txt"
    val rdd = spark.sparkContext.textFile(path).map(_.split(" ")).map(x => Stu(
      x(0), x(1), x(2).toInt, x(3), x(4), x(5).toInt
    ))

    val df = spark.createDataFrame(rdd)
    df.createTempView("p")

    spark.sql("select count(*) from (select name from p where age < 20 group by name)").show

    spark.sql("select count(*) from (select name from p where gender == '男' group by name)").show
  }
}
