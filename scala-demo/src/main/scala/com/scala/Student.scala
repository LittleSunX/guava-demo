package com.scala

/**
 * @author xys
 * @version 1.0.0
 * @createTime 2021年04月27日 10:39:00
 */
class Student(name: String, var age: Int) {

  def printlnInfo(): Unit = {
    println(name + " " + age + " " + " " + Student.school)
  }


}

//引入伴生对象
object Student {
  val school: String = "北大"

  def main(args: Array[String]): Unit = {
    val zs = new Student("zs", 18)
    val ls = new Student("ls", 19)
    zs.printlnInfo()
    ls.printlnInfo()
  }

}

