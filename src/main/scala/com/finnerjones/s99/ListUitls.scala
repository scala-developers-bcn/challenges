package com.finnerjones.s99

object ListUitls {

  // P01 on S99
  
  // my original solution
//  def last(ls:List[Any]):Any = 
//    ls.reverse.head
  
  // recursive solution, for practice
  def last(ls:List[Any]):Any = ls match {
    case elem :: Nil => elem
    case _ :: tail => last(tail)
    case _ => throw new NoSuchElementException
  } 
    
  // P02 on S99
  // my original solution
  def penultimate(ls:List[Any]):Any =
    ls.reverse.tail.head

}