package org.scalabcn.s99

import scala.annotation.tailrec

object Week1 {
  object Last {
    def trivial[T](xs: List[T]): T = xs.last

    def reversing[T](xs: List[T]): T = xs.reverse.head

    @tailrec
    def recursive[T](xs: List[T]): T = xs match {
      case x :: Nil => x
      case x :: xs => recursive(xs)
      case Nil => throw new NoSuchElementException
    }

    def fold[T](xs: List[T]): T = (xs.head /: xs)((a: T, b: T) => b)

    def funcs[T]: List[List[T] => T] = List(trivial[T], reversing[T], recursive[T], fold[T])
  }

  object Penultimate {
    def trivial[T](xs: List[T]): T = xs.init.last

    def reversing[T](xs: List[T]): T = xs.reverse.tail.head

    def sliding[T](xs: List[T]): T = xs.sliding(2).toList.last.head

    @tailrec
    def recursive[T](xs: List[T]): T = xs match {
      case x :: y :: Nil => x
      case x :: Nil => throw new NoSuchElementException
      case Nil => throw new NoSuchElementException
      case x :: xs => recursive(xs)
    }

    def funcs[T]: List[List[T] => T] = List(trivial[T], reversing[T], sliding[T], recursive[T])
  }

  object Nth {
    def trivial[T](pos: Int, xs: List[T]): T = xs(pos)

    @tailrec
    def recursive[T](pos: Int, xs: List[T]): T =
      if (pos == 0)
        xs.head
      else
        recursive(pos-1, xs.tail)

    def funcs[T]: List[(Int, List[T])=> T] = List(trivial[T], recursive[T])
  }
}
