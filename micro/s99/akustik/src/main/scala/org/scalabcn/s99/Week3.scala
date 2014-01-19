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

  //TODO: Find a tailrec version
  def combinations[T](k: Int, x: List[T]): List[List[T]] = {
    if(k > x.length || k == 0) Nil
    else if(k == 1) x.map(List(_))
    else {
      combinations(k, x.tail) ::: combinations(k - 1, x.tail).map(x.head :: _)
    }
  }

  //TODO: Find a tailrec version
  def group[T](g: List[Int], x: List[T]): List[List[List[T]]] = {
    g match {
      case y :: ys => combinations(y, x).flatMap(c => group(ys, x diff c).map(c :: _))
      case Nil => List(Nil)
    }
  }

  def lsort[T](x: List[List[T]]): List[List[T]] = {
    x.sortWith(_.length < _.length)
  }

  def lsortFreq[T](x: List[List[T]]): List[List[T]] = {
    var freqs = x.map(_.length).groupBy(i => i).map{case (k, v) => (k, v.length)}
    x.sortWith((a, b) => freqs(a.length) < freqs(b.length))
  }



}
