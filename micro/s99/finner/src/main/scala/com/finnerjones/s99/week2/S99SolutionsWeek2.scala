package com.finnerjones.s99.week2

import com.finnerjones.s99.ListUtils._

/*
 * taken from S-99 website
 * http://aperiodic.net/phil/scala/s-99/
 */
object S99SolutionsWeek2 {

  // P11 - my own solution, using pack(l) !!!
  def packModified(l:List[Any]):List[Any] = 
    pack(l) map { e =>
	  if (e.length > 1) (e.length,e.head)
	  else e.head
  	}
  
  
  // P11 - my own solution, this time using encode(l)
  def encodeModified(l:List[Any]):List[Any] =
    encode(l) map { e => if (e._1 > 1) e else e._2}
  
  
  // P11 - web solution 2, more typesafe
  def encodeModified2[A](l:List[A]):List[Either[A, (Int,A)]] =
    encode2(l) map { tuple => if (tuple._1 == 1) Left(tuple._2) else Right(tuple)}
  
  
  // P12 - my own solution !!!
  def decode[A](l:List[(Int,A)]):List[A] = 
    l flatMap { t => List.concat(List.fill(t._1)(t._2)) }
  
  
  // P12 - web solution
  def decode1[A](l:List[(Int,A)]):List[A] =
    l flatMap { e => List.make(e._1, e._2)}
  
  
  // P13 - my first draft solution, implementing pack and encode in one def 
  def encodeDirect[A](l:List[A]):List[(Int,A)] = {
	
    def packD[A](ls:List[A]):List[List[A]] = ls match {
      case Nil => Nil
      case h::tail => ls.takeWhile(_ == ls.head) :: packD(ls dropWhile(_ == ls.head))
    }
    
	val psD = packD(l)
	
	def encodeD[A](ps:List[List[A]]):List[(Int,A)] = ps match {
	  case Nil => Nil
	  case h::tail => (h.length,h.head) :: encodeD(tail)
	}
	  
	encodeD(psD)
  }

  // P13 - second draft solution based on the web pack() P09
  def encodeD2[A](l:List[A]):List[(Int,A)] = {
    
    val (packed, next) = l span { _ == l.head }
    if (next == Nil) List((packed.length,packed.head))
    else List((packed.length,packed.head)):::encodeD2(next)
  }
  
  // P13 - third draft solution base on my pack() solution P09
  def encodeD3[A](l:List[A]):List[(Int,A)] = l match {
    case Nil => Nil
    case h::tail => {
      val ls = (l.takeWhile(_ == l.head))
      List( (ls.length, ls.head)) ::: encodeD3(l dropWhile(_ == l.head))
    }
  }
}