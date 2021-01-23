package com.yglong.spark.scala.udaf

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, StructType}

object MyUDAF extends UserDefinedAggregateFunction {
  override def inputSchema: StructType = ???

  override def bufferSchema: StructType = ???

  override def dataType: DataType = ???

  override def deterministic: Boolean = true

  // 对局部聚合缓存的初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = ???

  // 聚合逻辑，框架会不断传入新的输入row，来更新聚合缓存数据
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = ???

  // 全局聚合：将多个局部缓存中的数据，聚合成一个缓存
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = ???

  // 最终输出
  override def evaluate(buffer: Row): Any = ???
}
