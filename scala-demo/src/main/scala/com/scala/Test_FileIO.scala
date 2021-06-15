package com.scala


import java.io.{File, PrintWriter}

import scala.io.Source

/**
 * @Author xys
 * @Version 1.0.0
 * @Description 文件输入输出
 * @createTime 2021年04月27日 14:27:00
 */
object Test_FileIO {
  def main(args: Array[String]): Unit = {

    //从文件读取数据
    Source.fromFile("src/main/resources/test.txt").foreach(print)
    //写入数据到文件
    val writer= new PrintWriter(new File("src/main/resources/test.txt"))
    writer.write("java io hello")
    writer.close()
  }

}
