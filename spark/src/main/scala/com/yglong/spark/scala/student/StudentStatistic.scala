package com.yglong.spark.scala.student

import org.apache.spark.{SparkConf, SparkContext}

object StudentStatistic {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("StudentStatistic").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //    1. 读取文件的数据test.txt
    val data = sc.textFile("C:\\ylong\\workspace\\bigdata\\study\\spark\\data\\student\\student.txt")

    //    2. 一共有多少个小于20岁的人参加考试？
    println(data.map(_.split(" ")).filter(_(2).toInt < 20).groupBy(_(1)).count())

    //    3. 一共有多少个等于20岁的人参加考试？
    println(data.map(_.split(" ")).filter(_(2).toInt == 20).groupBy(_(1)).count())

    //    4. 一共有多少个大于20岁的人参加考试？
    println(data.map(_.split(" ")).filter(_(2).toInt > 20).groupBy(_(1)).count())

    //    5. 一共有多个男生参加考试？
    println(data.map(_.split(" ")).filter(_(3).equals("男")).groupBy(_(1)).count())

    //    6. 一共有多少个女生参加考试？
    println(data.map(_.split(" ")).filter(_(3).equals("女")).groupBy(_(1)).count())

    //    7. 12班有多少人参加考试？
    println(data.map(_.split(" ")).filter(_(0).equals("12")).groupBy(_(1)).count())

    //    8. 13班有多少人参加考试？
    println(data.map(_.split(" ")).filter(_(0).equals("13")).groupBy(_(1)).count())

    //    9. 语文科目的平均成绩是多少？
    println(data.map(_.split(" ")).filter(_(4).equals("chinese")).map(_(5).toInt).mean())

    //   10. 每个人平均成绩是多少？
    println(data.map(_.split(" ")).map(x => (x(1), x(5).toInt)).groupByKey().map(t => (t._1, t._2.sum / t._2.size)))


  }

}
