package com.scala

/**
 * @Author xys
 * @Version 1.0.0
 * @Description
 * @createTime 2021年04月27日 16:46:00
 */
object Test03 {
  def main(args: Array[String]): Unit = {
    //比较运算符
    val s1 = "hello"
    val s2 = new String("hello")
    val b = s1 == s2;
    println(b) //true
    println(s1.eq(s2)) //false  eq方法用来判断引用地址是否相等相当于java中的==
    println(s1.equals(s2)) //true
    //总结
    //scala摒弃了java比较引用地址的方式直接专注内容比较
    println("-----------------------------------------------")

    //逻辑运算符
    def m(n: Int): Int = {
      println("m方法被调用")
      return n
    }
    println((5 > 4) && m(1) > 0)
    //判断字符串不为空
    def isNotEmpty(str: String): Boolean = {
      return str !=null && !"".equals(str.trim)
    }
    println(isNotEmpty("str"))
    //总结逻辑运算符和java用法一致
    println("合并分支代码测试")


  }

}
