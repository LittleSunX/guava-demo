package com.scala

import scala.language.postfixOps

/**
 * @Author xys
 * @Version 1.0.0
 * @Description scala中的for循环运用
 * @createTime 2021年04月28日 15:00:00
 */
object Test05 {
  def main(args: Array[String]): Unit = {
    //范围遍历
    //to表示到1到10
    for (i <- 1 to 10) {
      println(i) //打印1到10
    }
    //until表示到为止1到10但是不包含10
    for (i <- 1 until 10) {
      println(i) //打印1到9
    }
    println("-----------------------")
    //遍历集合
    for (i <- List(4, 5, 6)) {
      println(i)
    }
    //循环步长 by 1+3  4+3 7+3
    for (i <- 1 to 10 by 3) {
      println(i)
    }
    //倒叙打印  reverse翻转
    //需要导入import scala.language.postfixOps包不然会报错
    for (i <- 1 to 10 reverse) {
      println(i)
    }

    //循环嵌套
    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        println("i= " + i + " " + "j= " + j)
      }
    }
    println("--------------")
    //简写
    for (i <- 1 to 3; j <- 1 to 3) {
      println("i= " + i + " " + "j= " + j)
    }
    println("----------------------")
    //打印9*9乘法表
    for (i <- 1 to 9; j <- 1 to i) {
      print(s"${j} * ${i} = ${j * i} \t")
      if (j == i)
        println()
    }
  }

}
