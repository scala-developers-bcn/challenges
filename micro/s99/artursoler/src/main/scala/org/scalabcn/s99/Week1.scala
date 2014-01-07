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

  object Length {
    def trivial[T](xs: List[T]): Int = xs.length

    def recursive[T](xs: List[T]): Int = xs match {
      case Nil => 0
      case x :: xs => 1 + recursive(xs)
    }

    def recursiveTailrec[T](xs: List[T]) = {
      @tailrec
      def inner[T](xs: List[T], acc: Int): Int = xs match {
        case Nil => acc
        case x :: xs => inner(xs, acc+1)
      }
      inner(xs, 0)
    }

    def fold[T](xs: List[T]) = (xs :\ 0)((elem, acc) => acc+1)

    def funcs[T]: List[List[T] => Int] = List(trivial[T], recursive[T], recursiveTailrec[T], fold[T])
  }

  object Reverse {
    def trivial[T](xs: List[T]) = xs.reverse

    def recursive[T](xs: List[T]): List[T] = xs match {
      case Nil => Nil
      case x :: xs => recursive(xs) ::: List(x)
    }

    def recursiveTailrec[T](xs: List[T]) = {
      def inner[T](xs: List[T], acc: List[T]): List[T] = xs match {
        case Nil => acc
        case x :: xs => inner(xs, x :: acc)
      }
      inner(xs, List[T]())
    }

    def fold[T](xs: List[T]) = (List[T]() /: xs)((acc, x) => x :: acc)

    def funcs[T]: List[List[T] => List[T]] = List(trivial[T], recursive[T], recursiveTailrec[T], fold[T])
  }
}
