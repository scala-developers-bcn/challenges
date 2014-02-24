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


  // P07 
  def flatten(l:List[Any]):List[Any] = l flatMap {
    case ls: List[_] => flatten(ls)
    case e => List(e)
  }

  // P08
  def compressRecursive(l:List[Any]):List[Any] = l match {
    case Nil => Nil
    case h :: tail => h::compressRecursive(tail.dropWhile(_ == h))
  } 
  
  def compressTailRecursive[A](l:List[A]):List[A] = {
    def compressR(result:List[A], curList:List[A]):List[A] = curList match {
      case h::tail => compressR(h::result,tail.dropWhile(_ == h))
      case Nil => result.reverse
    }
    compressR(Nil,l)
    
  }
  
  
  def compressFunctional[A](l:List[A]):List[A] = 
    l.foldRight(List[A]())  { (head,rest) =>
    	if (rest.isEmpty || rest.head != head) head::rest
    	else rest
  	}
  
  
  // P09 - my very own solution !!!!
  def pack(l:List[Any]): List[List[Any]] = l match {
    case Nil => Nil
    case h::tail => l.takeWhile(_ == l.head) :: pack(l dropWhile(_ == l.head))
  }
  
  // P09 - this solution is from the website
  def pack2[A](l:List[A]): List[List[A]] = {
    if (l.isEmpty) List(List())
    else { 
      /*
       * example using span
       * val l = List(1,1,1,1,2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 4, 4, 4, 4)
       * l span {_ == l.head}
       * res13: (List[Int], List[Int]) = 
       * (List(1, 1, 1, 1),List(2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 4, 4, 4, 4))
       * (   packed       ,     next      )
       */     
      val (packed, next) = l span { _ == l.head }
      if (next == Nil) List(packed)
      else packed::pack2(next)
    }
  }
  
  // P10 - my very own solution !!
  def encode(l:List[Any]):List[(Int, Any)] = {
    val c = pack(l)
    
    def enc(l:List[List[Any]]):List[(Int,Any)] = l match {
      case Nil => Nil
      case h::tail => (h.length,h.head) :: enc(tail)
    }
    enc(c)
  }
  
  // P10 - solution from website
  def encode2[A](l:List[A]):List[(Int,A)] = {
    pack2(l) map { elem => (elem.length, elem.head)}
  }
}