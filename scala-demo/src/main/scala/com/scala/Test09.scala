package com.scala

object Test09 {
  def main(args: Array[String]): Unit = {
    println(test())
  }

  def test(): String = {
    val list = Array(10, 20, 30)
    if (list.length < 0) {
      return "list无数据"
    }
    if (list.length > 2) {
      return "list数据大于2"
    }
    "list有数据"
  }
}
