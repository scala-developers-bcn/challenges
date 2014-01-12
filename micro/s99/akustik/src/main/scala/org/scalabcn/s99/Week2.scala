package org.scalabcn.s99

import scala.annotation.tailrec
import org.scalabcn.s99.Week1._

object Week2 {

  def encodeModified[T](x: List[T]): List[Any] = {
    pack(x).map(same => {
      if(same.length > 1)  (same.length, same.head)
      else same.head
    })
  }

  def decode[T](x: List[(Int, T)]): List[T] = x.flatMap(pair => List.fill(pair._1)(pair._2))
  
  def encodeDirect[T](x: List[T]): List[(Int, T)] = {
    if(!x.isEmpty) x.tail.foldLeft(List((1, x.head)))((enc, x) => {
      if(enc.head._2 == x) (enc.head._1 + 1, x) :: enc.tail 
      else (1, x) :: enc
    }).reverse
    else Nil
  } 

  def duplicate[T](x: List[T]): List[T] = x.flatMap(e => List.fill(2)(e))

  def duplicateN[T](times: Int, x: List[T]): List[T] = x.flatMap(e => List.fill(times)(e))

  def drop[T](n: Int, x: List[T]): List[T] = {
    if(n < 1) throw new java.lang.IllegalArgumentException
    else x.foldLeft((n, List[T]()))((acc, x) => {
      if(acc._1 == 1) (n, acc._2)
      else (acc._1 - 1, x :: acc._2)
    })._2.reverse
  }

  def split[T](len: Int, x: List[T]): (List[T], List[T]) = {
    if(len < 0 || len > x.length) throw new java.lang.IllegalArgumentException
    val tuple = x.foldLeft((List[T](), List[T]()))((acc, x) => {
      if(acc._1.length == len) (acc._1, x :: acc._2)
      else (x :: acc._1, acc._2) 
    })
    (tuple._1.reverse, tuple._2.reverse)
  }
  
  def slice[T](from: Int, until: Int, x: List[T]): List[T] = {
    if(from < 0 || until < from) throw new java.lang.IllegalArgumentException
    x.foldLeft((0, List[T]()))((acc, x) => {
      (acc._1 + 1, if(acc._1 >= from && acc._1 < until) x :: acc._2 else acc._2)
    })._2.reverse
  }

  def rotate[T](n: Int, x: List[T]): List[T] = {
    if(n >= 0) {
      val parts = split(n % x.length, x)
      parts._2 ::: parts._1
    } else {
      val parts = split(x.length + (n % x.length), x)
      parts._2 ::: parts._1
    }
  }
}
