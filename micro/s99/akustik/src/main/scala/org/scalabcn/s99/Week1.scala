package org.scalabcn.s99

import scala.annotation.tailrec

object Week1 {

  def last[T](x: List[T]) = x.reverse match {
    case x :: xs => x
    case _ => throw new IllegalArgumentException("List not long enough")
  }

  def penultimate[T](x: List[T]) = x.reverse match {
    case x :: y :: xs => y
    case _ => throw new IllegalArgumentException("List not long enough")
  }

  def nth[T](i: Int, x: List[T]) = {
    @tailrec
    def nthv[T](i: Int, x: List[T]): T = i match {
      case 0 => x.head
      case _ => nthv(i - 1, x.tail)
    }
    if (x.length < i + 1) throw new IndexOutOfBoundsException(s"Index ${i} out of bounds")
    nthv(i, x)
  }

  def length[T](x: List[T]) = {
    @tailrec
    def lengthAcc(l: Int, x: List[T]): Int = x match {
      case Nil => l
      case y :: ys => lengthAcc(l + 1, ys)
    }
    lengthAcc(0, x)
  }

  def reverse[T](x: List[T]) = {
    @tailrec
    def reverseAcc(acc: List[T], x: List[T]): List[T] = x match {
      case Nil => acc
      case e :: es => reverseAcc(e :: acc, x.tail)
    }
    reverseAcc(Nil, x)
  }

  def isPalindrome[T](x: List[T]) = {
    x.reverse == x
  }

  def flatten[T](x: List[Any]): List[T] = {
    @tailrec
    def flattenAcc[T](acc: List[T], x: List[Any]): List[T] = x match {
      case Nil => acc
      case y :: ys => y match {
        case l: List[Any] => flattenAcc(acc ::: flatten(l), ys)
        //TODO: Fix the warning
        case e: T => flattenAcc(acc :+ e, ys)
      }
    }
    flattenAcc(Nil, x)
  }

  def compress[T](x: List[T]): List[T] = {
    @tailrec
    def compressAcc[T](acc: List[T], x: List[T]): List[T] = x match {
      case Nil => acc
      case y :: ys => if (last(acc) != y) compressAcc(acc :+ y, ys) else compressAcc(acc, ys)
    }

    x match {
      case Nil => Nil
      case y :: ys => compressAcc(List(y), ys)
    }
  }

  def pack[T](x: List[T]): List[List[T]] = {
    @tailrec
    def packAcc[T](acc: List[List[T]], same: List[T], x: List[T]): List[List[T]] = x match {
      case Nil => acc :+ same
      case y :: ys => {
        if (y == same.head) packAcc(acc, y :: same, ys)
        else packAcc(acc :+ same, List(y), ys)
      }
    }

    x match {
      case Nil => Nil
      case y :: ys => packAcc(Nil, List(y), ys)
    }
  }

  def encode[T](x: List[T]): List[(Int, T)] = {
    pack(x).map(same => (same.length, same.head))
  }

}
