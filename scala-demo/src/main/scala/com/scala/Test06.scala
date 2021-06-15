package com.scala

/**
 * @author xys
 * @version 1.0.0
 * @createTime 2021年04月27日 10:39:00
 */
object Test06 {
  def main(args: Array[String]): Unit = {
    //调用方法
    //    sayHi("ali")
    //    println( sum(5,6))
    //可变参数
    def t1(str: String*): Unit = {
      println(str)
    }

    t1("aaa", "bbb", "ccc") //得到类似一个array数组
    //多个参数，可变参数一般放置到最后
    def t2(s: String, str: String*): Unit = {
      println(s, str)
    }

    t2("sss", "sssa", "222", "666") //前面是一个单独的字符串后面是一个类似array的数组
    //给参数设置默认值
    def t3(name: String = "李四"): Unit = {
      println("我的名字是" + name)
    }

    t3()
    t3("孜孜")

    //带名参数
    def t4(name: String = "孜孜", age: Int): Unit = {
      println(s"${name}今年${age}岁啦")
    }
    //调用
    t4("莉莉", 15)
    //有默认值可以通过带名参数的方式传参
    t4(age = 19)
    //函数至简
    def t5(name: String) = name
    println(t5("李丽"))
    //匿名函数
    (name: String)=>{println(name)}

  }

  def sayHi(name: String): Unit = {
    println("hi, " + name)
  }

  //多个参数有返回值
  def sum(a: Int, b: Int): Int = {
    return a + b
  }
}
