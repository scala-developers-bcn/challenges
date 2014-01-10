package org.scalabcn.s99

import scala.annotation.tailrec

object Week1 {
  def last[T](xs: List[T]) = (xs.head /: xs)((discard: T, keep: T) => keep)

  // xs.init.last being the obvious
  @tailrec
  def penultimate[T](xs: List[T]): T = xs match {
    case x :: y :: Nil => x
    case x :: Nil => throw new NoSuchElementException
    case Nil => throw new NoSuchElementException
    case x :: xs => penultimate(xs)
  }

  @tailrec
  def nth[T](pos: Int, xs: List[T]): T =
    if (pos == 0) xs.head
    else nth(pos-1, xs.tail)

  def length[T](xs: List[T]) = (xs :\ 0)((elem, acc) => acc+1)

  def reverse[T](xs: List[T]) = (List[T]() /: xs)((acc, x) => x :: acc)
  }
