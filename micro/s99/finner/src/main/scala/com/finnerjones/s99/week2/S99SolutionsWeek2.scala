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
  
  //P14  - my solution 
  def duplicate[A](l:List[A]):List[A] =
    l flatMap (elem => List(elem,elem))
  
  
  // P15 - my solution
  def duplicateN[A](n:Int, l:List[A]): List[A] =
    l flatMap (elem => List.fill(n)(elem))
  
  // P15 from website
  def duplicate2N[A](n:Int, l:List[A]):List[A] =
    l flatMap { List.make(n, _) }
  
  
  // P16 - my solution
  def drop[A](n:Int,l:List[A]):List[A] = 
    l filter (e => (l.indexOf(e)+1) % 3 != 0)


  // P16 - web solution 1
  def dropRecursive[A](n:Int, l:List[A]):List[A] = {
    def dropR(c:Int, curList:List[A]) : List[A] = (c,curList) match {
      case (_, Nil) => Nil
      case (1, _::tail) => dropR(n,tail)
      case (_, h::tail) => h :: dropR(c- 1, tail)
    }
    dropR(n,l)
    
  }

  // P16 - web solution 2
  def dropTailRecursive[A](n:Int, l:List[A]):List[A] = {
    def dropR(c:Int, curList:List[A], result:List[A]):List[A] = (c,curList) match {
      case (_, Nil) => result.reverse
      case (1, _::tail) => dropR(n,tail,result)
      case (_, h::tail) => dropR(c - 1, tail, h::result)
    }
    dropR(n,l,Nil)
  }

  // P16 - web solution 3 (similar to mine)
  def dropFunctional[A](n:Int, l:List[A]): List[A] =
    l.zipWithIndex filter { v => (v._2 +1) % n != 0} map { _._1 }


}