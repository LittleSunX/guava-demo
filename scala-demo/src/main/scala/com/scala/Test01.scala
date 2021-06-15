package com.scala

/**
 * @author xys
 * @version 1.0.0
 */
object Test01 {
  def main(args: Array[String]): Unit = {
    //    val ls = new Student("ls", 18);
    //    ls.age=19;
    //    ls.printlnInfo()
    val name = "zs"
    val age = 10
    //拼接字符串
    println(name + "今年" + age + "岁")
    //表示name乘2
    println(name * 2)
    //s字符串模板使用
    println(s"${name}今年${age}岁")
    //三引号运用
    val sql =
      s"""
         |select *
         |from
         |student
         |where
         | name=$name
         |and
         | age=$age
         |""".stripMargin
    println(sql)
  }

}
