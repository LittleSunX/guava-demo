package com.scala

/**
 * scala中集合用法
 */
object Test08 {
  def main(args: Array[String]): Unit = {
    val arr = Array(10, 20, 30)
    for (elem <- arr) {
      println(elem)
    }
    arr.foreach(elem => println(elem))
    println("----------------------")
    arr.foreach(println)
    println("-----------------------------")
    //把集合中的第二个元素改为60
    arr.update(1, 60)
    println(arr.mkString(","))


  }

}
