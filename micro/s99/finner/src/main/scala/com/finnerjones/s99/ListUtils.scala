package com.finnerjones.s99

/*
 * The S-99 scala katas are here:
 * http://aperiodic.net/phil/scala/s-99/ 
 */

object ListUtils {

  // P01 on S99
  def last(ls:List[Any]):Any = 
    ls.reverse.head
  
    
  // P02 on S99
  def penultimate(ls:List[Any]):Any =
    ls.reverse.tail.head

  // P03 on S99
  def nth(idx:Int, ls:List[Any]):Any = 
    ls(idx)

  // P04 on S99
  def lngth(ls:List[Any]): Any =
    ls length

  def lengthTailRecursive[A](ls:List[A]) :Any = {
    def lengthR(result:Int, curList: List[A]): Int = curList match {
      case Nil => result
      case _ :: tail => lengthR(result + 1, tail)
    }
    lengthR(0,ls)
  }  
  

  // P05
  def reverse[A](ls:List[A]):List[A] = ls match {
    case h :: Nil => List(h) 
  	case h :: tail => reverse(tail) ::: List(h) 
  }
  
  // P06
  def isPalindrome[A](ls:List[A]): Boolean =  
    ls == ls.reverse
}