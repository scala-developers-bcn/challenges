package com.agilogy

class S99Challenge {

  def last(x: List[Int]) = x match {
    case Nil => Nil
    case xs => xs.reverse.head
  }

}
