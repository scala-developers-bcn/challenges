package org.scalabcn.s99

import scala.annotation.tailrec
import org.scalabcn.s99.Week2.removeAt

object Week3 {

  def insertAt[T](element: T, pos: Int, x: List[T]): List[T] = {
    if(pos < 0 || pos > x.length) throw new java.lang.ArrayIndexOutOfBoundsException
    if(pos == x.length) (element :: x.reverse).reverse
    else x.foldLeft((List[T](), 0))((acc, e) => {
     acc match {
       case (xs, idx) if(idx == pos) => (e :: element :: xs, idx + 1)
       case (xs, idx) => (e :: xs, idx + 1) 
     }
    })._1.reverse
  } 

  def range(a: Int, b:Int) = {
    if(a > b) throw new java.lang.IllegalArgumentException
    (0 to (b - a)).map(i => i + a)
  }

  def randomSelect[T](amount: Int, x: List[T]): List[T] = {
    val r = new java.util.Random
    
    @tailrec
    def randomSelectWhileAmount(amount: Int, x: List[T], acc: List[T]): List[T] = {
      if(amount == 0) acc
      else {
        val res = removeAt(r.nextInt(x.length), x)
        randomSelectWhileAmount(amount - 1, res._1, res._2 :: acc)
      } 
    }

    randomSelectWhileAmount(amount, x, Nil)
  }

  def lotto(amount: Int, m: Int) = {
    val values = (1 to m).toList
    randomSelect(amount, values)
  } 

  def randomPermute[T](x: List[T]): List[T] = {
    randomSelect(x.length, x)
  }

}
