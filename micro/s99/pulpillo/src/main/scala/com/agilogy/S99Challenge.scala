package com.agilogy
//
class S99Challenge {

  def last(x: List[Int]) = x match {
    case Nil => Nil
    case xs => xs.reverse.head
  }

  def penultimate(x: List[Int]) = x match {
    case Nil => Nil
    case xs::Nil => Nil
    case xs => xs.reverse.tail.head
  }

  def nth(pos: Int, x: List[Int]) = {
    if(pos < 0 || pos > (x.size - 1)) Nil
    else x.toVector(pos)

  }

  def length(x: List[Int]) = {
    x.foldLeft(0)((r,c) => r + 1)
  }

  def reverse[A](x : List[A]) = {
    x.foldLeft(List[A]())((r,c) => c :: r)
  }

  def isPalindrome(x: List[Int]) = {
    x == x.reverse
  }

  def flatten(x: List[Any]): List[Any] = x match {
    case Nil => Nil
    case (head : List[_]) :: tail => flatten(head) ++ flatten(tail)
    case head :: tail => head :: flatten(tail)
  }

  def compress[A](x: List[A]): List[A] = {
    x.foldRight(List[A]())((elem,acum) =>
      if(acum.isEmpty || elem != acum.head)
        elem :: acum
      else
        acum
    )
  }

  def pack[A](x : List[A]) = {
    x.foldRight(List[List[A]]()){
      (elem,acum) =>
        if(acum.isEmpty || acum.head.head != elem) List(elem) :: acum
        else  (elem :: acum.head) :: acum.tail
    }
  }

  def encode[A](x : List[A]) = {
    val packed = pack(x)
    packed.map(elem => (elem.size,elem.head))
  }

  def encodeModified[A](x : List[A]) = {
    val packed = pack(x)
    packed.map(elem => if(elem.size > 1)(elem.size,elem.head) else elem.head)
  }

  def decode[A](x : List[(Int,A)]) = {

    def expand(x : (Int,A)): List[A] =  x._1 match{
      case 0 => List()
      case n => x._2::expand((x._1 - 1,x._2))
    }

    x.flatMap(expand(_))
  }

  def encodeDirect[A](x : List[A]) = {
    (x.foldRight(List[List[A]]()){
      (elem,acum) =>
        if(acum.isEmpty || acum.head.head != elem) List(elem) :: acum
      else  (elem :: acum.head) :: acum.tail
    }).map(elem => (elem.size,elem.head))
  }

  def duplicate[A](x : List[A]) = {
    x.flatMap(List.fill(2)(_))
  }

  def duplicateN[A](n: Int, x : List[A]) = {
    x.flatMap(List.fill(n)(_))
  }

  def drop[A](n: Int, x : List[A]): List[A] =  x match {
    case Nil => Nil
    case xs => {
      val (l,r) = xs splitAt(n - 1)
      if(r.isEmpty)
        l
      else
        l ++ drop(n,r.tail)
    }
  }

  def split[A](n: Int, x : List[A]):(List[A],List[A]) =  x splitAt(n)

  def slice[A](from: Int, to: Int, x : List[A]) =  x slice(from,to)
}

