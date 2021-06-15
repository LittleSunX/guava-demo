package com.scala

/**
 * @Author xys
 * @Version 1.0.0
 * @Description lambda表达式
 * @createTime 2021年04月29日 10:45:00
 */
object Test07 {
  def main(args: Array[String]): Unit = {
    //匿名函数
    val t = (name: String) => {
      println(name)
    }
    t("莉莉")

    //定义函数把函数以参数传入
    def fun(func: String => Unit): Unit = {
      func("孜孜")
    }

    fun(t)
  }

}
