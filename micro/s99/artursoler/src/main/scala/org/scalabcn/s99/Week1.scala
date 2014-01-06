package org.scalabcn.s99

import scala.annotation.tailrec

object Week1 {
  object P01 {
    def trivial[T](xs: List[T]) = xs.last

    @tailrec
    def recursive[T](xs: List[T]): T = xs match {
      case x :: Nil => x
      case x :: xs => recursive(xs)
      case Nil => throw new NoSuchElementException
    }
  }
}
