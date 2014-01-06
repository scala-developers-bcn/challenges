package org.scalabcn.s99

import scala.annotation.tailrec

object Week1 {
  object Last {
    def trivial[T](xs: List[T]): T = xs.last

    def composing[T](xs: List[T]): T = xs.reverse.head

    @tailrec
    def recursive[T](xs: List[T]): T = xs match {
      case x :: Nil => x
      case x :: xs => recursive(xs)
      case Nil => throw new NoSuchElementException
    }

    def fold[T](xs: List[T]): T = (xs.head /: xs)((a: T, b: T) => b)

    def funcs[T]: List[(List[T]) => T] = List(trivial[T], composing[T], recursive[T], fold[T])
  }
}
