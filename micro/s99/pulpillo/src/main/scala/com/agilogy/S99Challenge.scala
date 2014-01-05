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
}

