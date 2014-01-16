package com.agilogy

import scala.annotation.tailrec

class S99Challenge {

  def last(x: List[Int]) = if (x.isEmpty) Nil else x.foldLeft(x.head)((_,c) => c)

  def penultimate(x: List[Int]) = if (x.size < 2) Nil else x.foldLeft((x.head, x.tail.head))((r,c) => (r._2,c))._1

  def nth(position: Int, x: List[Int]):Int =
    if (x.isEmpty)
      throw new IndexOutOfBoundsException
    else if (position == 0)
      x.head
    else {
      nth(position-1,x.tail)
    }

  def length[T](xs:List[T]):Int = xs match {
    case Nil => 0
    case _ => 1 + length(xs.tail)
  }

  def reverse[T](xs: List[T]):List[T] = reverse_folding(xs)

  private def reverse_folding[T](xs: List[T]):List[T] =
    (List[T]() /: xs)((acc, x) => x :: acc) //=== xs.foldLeft(List[T]())((acc, x) => x :: acc)

  private def reverse_recursive[T](xs: List[T]):List[T] =
  {
    def helper(acc:List[T], actual:List[T]): List[T] = actual match {
      case Nil => acc
      case x :: ys => helper(x :: acc, ys)
    }
    helper(Nil, xs)
  }

  def isPalindrome[T](xs: List[T]): Boolean =
    xs.take(xs.length/2).reverse == xs.takeRight(xs.length/2)

  def flatten(xs: List[_]):List[_] = xs flatMap {
    case a: List[_] => flatten(a)
    case x => List(x)
  }

  def compress[T](xs: List[T]):List[T] = xs.foldRight(List[T]()) {
    (x, list) => {
      if (list.isEmpty || list.head != x) x :: list
      else list
    }
  }

  def pack[T](xs: List[T]): List[List[T]] = xs.foldRight(List[List[T]]()) {
    (x, list) => {
      if (list.isEmpty || list.head.head != x) List(x) :: list
      else (x :: list.head) :: list.tail
    }
  }

  def encode[T](xs:List[T]): List[(Int, T)] = pack(xs).map(list => (list.size,list.head))

  def encodeModified[T](xs:List[T]): List[Any] = pack(xs).map(list => {
    if (list.size > 1) (list.size,list.head)
    else list.head
  })

  def decode[T](xs: List[(Int, T)]):List[T] = xs.flatMap(e => (1 to e._1).map(_ => e._2))

  def encodeDirectRecursive(xs: List[Symbol]): List[(Int, Symbol)] = {
    @tailrec
    def encodeDirectHelper(xs: List[Symbol], acc: List[(Int, Symbol)]): List[(Int, Symbol)] = xs match {
      case Nil => acc
      case x :: ys if acc.isEmpty =>
        encodeDirectHelper(ys, List((1, x)))
      case x :: ys if acc.head._2 == x =>
        encodeDirectHelper(ys, (acc.head._1+1,acc.head._2) :: acc.tail)
      case x :: ys if acc.head._2 != x =>
        encodeDirectHelper(ys, (1,x) :: acc)
    }
    encodeDirectHelper(xs, Nil).reverse
  }

  def encodeDirect[A](ls: List[A]): List[(Int, A)] =
    if (ls.isEmpty) Nil
    else {
      val (packed, next) = ls span { _ == ls.head }
      (packed.length, packed.head) :: encodeDirect(next)
    }

  def duplicate[T](xs: List[T]): List[T] =
    xs.flatMap(x => List(x,x))

  def duplicateN[T](n:Int, xs:List[T]): List[T] =
    xs.flatMap(x => List.fill(n)(x))

  def drop[T](n: Int, xs:List[T]): List[T] =
    if (xs.isEmpty) Nil
    else xs.take(n-1) ::: drop(n,xs.drop(n))

  def split[T](n: Int, xs: List[T]) : (List[T], List[T]) =
    (xs.take(n), xs.drop(n))

  def slice[T](i:Int, k:Int, xs:List[T]):List[T] = Nil

  def rotate[T](k:Int, xs:List[T]):List[T] = Nil

  def removeAt[T](k:Int, xs:List[T]):(List[T],T) =
    removeAt2(k,xs)

  private def removeAt1[T](k:Int, xs:List[T]):(List[T],T) =
    if (k<0 || k>xs.length) throw new NoSuchElementException
    else (xs.take(k):::xs.drop(k+1),xs(k))

  private def removeAt2[T](k:Int, xs:List[T]):(List[T],T) =
    xs.splitAt(k) match {
      case (_,Nil) => throw new NoSuchElementException
      case (_,_) if k < 0 => throw new NoSuchElementException
      case (left, x :: right) => (left:::right, x)


    }

}
