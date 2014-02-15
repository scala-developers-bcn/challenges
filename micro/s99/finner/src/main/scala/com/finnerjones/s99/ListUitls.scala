package com.finnerjones.s99

object ListUitls {

  // P01 on S99
  
  // my original solution
  def last(ls:List[Any]):Any = 
    ls.reverse.head
  
    
  // P02 on S99
  // my original solution
  def penultimate(ls:List[Any]):Any =
    ls.reverse.tail.head

  // P03 on S99
  // my original solution
  def nth(idx:Int, ls:List[Any]):Any = 
    ls(idx)
}