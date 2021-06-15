package com.scala

import scala.io.StdIn

/**
 * @Author xys
 * @Version 1.0.0
 * @Description StdIn初体验
 * @createTime 2021年04月27日 14:19:00
 */
object Test02 {
  def main(args: Array[String]): Unit = {
    //输入信息
    println("请输入名字")
    val name = StdIn.readLine()
    println("请输入年龄")
    val age = StdIn.readInt()
    println(s"${name}今年${age}岁")
  }

}
