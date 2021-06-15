package com.scala

import scala.io.StdIn

/**
 * @Author xys
 * @Version 1.0.0
 * @Description scala中if else的使用
 * @createTime 2021年04月28日 14:24:00
 */
object Test04 {
  def main(args: Array[String]): Unit = {
    println("请输入年龄")
    val age: Int = StdIn.readInt()
    val result: String = if (age >= 18 && age <= 20) {
      println("年龄成年")
      "年龄成年"
    } else if (age < 18) {
      println("未成年")
      "未成年"
    } else if (age >= 30 && age < 60) {
      println("青年")
      "青年"
    } else if (age >= 60) {
      println("老年")
      "老年"
    } else {
      println("外星人")
      "外星人"
    }
    //在scala中 if else 判断是可以有返回值的
    println("result=" + result)
  }

}
