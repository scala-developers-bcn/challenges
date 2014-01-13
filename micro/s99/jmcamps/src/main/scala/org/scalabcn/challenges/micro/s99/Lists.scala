package org.scalabcn.challenges.micro.s99

import java.util.NoSuchElementException
import scala.annotation.tailrec
import scala.collection.immutable.Nil

object Lists {

  def last[T](xs: List[T]): T = xs match {
    case Nil => throw new IllegalArgumentException
    case x => x.reverse.head
  }

  def penultimate[T](xs: List[T]): T = xs match {
    case Nil => throw new IllegalArgumentException
    case x :: Nil => throw new IllegalArgumentException
    case x => x.reverse.tail.head
  }

  def nth[T](position: Int, xs: List[T]): T = {
    @tailrec
    def loop[T](actual: Int, xs: List[T]): T = xs match {
      case Nil => throw new NoSuchElementException
      case x :: xs => if (actual == position) x else loop(actual + 1, xs)
    }

    loop(0, xs)
  }

  def length[T](xs: List[T]): Int = {
    @tailrec
    def loop[T](actual: Int, xs: List[T]): Int = xs match {
      case Nil => actual
      case x :: xs => loop(actual + 1, xs)
    }

    loop(0, xs)
  }

  def lengthWithFold[T](xs: List[T]): Int = xs.foldLeft(0)((sum, x) => sum + 1)

  def reverse[T](xs: List[T]): List[T] = xs match {
    case Nil => Nil
    case x :: xs => reverse(xs) :+ x
  }

  def reverseNotLinear[T](xs: List[T]): List[T] = {
    @tailrec
    def loop[T](reversed: List[T], actual: List[T]): List[T] = actual match {
      case Nil => reversed
      case x :: xs => loop(x :: reversed, xs)
    }

    loop(Nil, xs)
  }

  def reverseWithFold[T](xs: List[T]): List[T] = xs.foldLeft(List[T]())((accumulated, element) => element :: accumulated)

  def isPalindrome[T](xs: List[T]): Boolean = xs.reverse == xs

  def flatten[T](xs: List[Any]): List[T] = {
    @tailrec
    def loop[T](flattened: List[T], actual: List[Any]): List[T] = actual match {
      case Nil => flattened
      case y :: ys => y match {
        case sublist: List[Any] => loop(flattened ::: flatten(sublist), ys)
        case element: T => loop(flattened :+ element, ys)
      }
    }

    loop(Nil, xs)
  }

  def compress[T](list: List[T]): List[T] = {
    @tailrec
    def loop[T](compressed: List[T], actual: List[T]): List[T] = actual match {
      case Nil => compressed
      case y :: ys => compressed match {
        case Nil => loop(List(y), ys)
        case _ => if (y == compressed.last) loop(compressed, ys) else loop(compressed :+ y, ys)
      }
    }

    loop(Nil, list)
  }

  def compressWithFold[T](xs: List[T]): List[T] = {
    xs.foldLeft(List[T]())((accumulated, element) => accumulated match {
      case Nil => accumulated :+ element
      case _ => if (element != accumulated.last) accumulated :+ element else accumulated
    })
  }

  def pack[T](list: List[T]): List[List[T]] = {
    @tailrec
    def loop[T](packed: List[List[T]], sublist: List[T], actual: List[T]): List[List[T]] = actual match {
      case Nil => if (packed == Nil) Nil else packed :+ sublist
      case y :: ys => sublist match {
        case Nil => loop(packed, List(y), ys)
        case _ => if (y == sublist.head) loop(packed, y :: sublist, ys) else loop(packed :+ sublist, List(y), ys)
      }
    }

    loop(Nil, Nil, list)
  }

  def encode[T](list: List[T]): List[(Int, T)] = {
    pack(list).map(x => (x.length, x.head))
  }

  def encodeWithoutOneElementTupples[T](list: List[T]): List[Any] = {
    pack(list) map { x =>
      x.length match {
        case 1 => x.head
        case _ => (x.length, x.head)
      }
    }
  }
  
  def decode[T](list: List[(Int, T)]): List[T] = { 
    @tailrec
    def loop[T](decoded: List[T], actual: List[(Int, T)]): List[T] = actual match {
      case Nil => decoded
      case y :: ys => loop(decoded ++ List.fill(y._1)(y._2), ys)
    }

    loop(Nil, list)
  }
  
  def decodeWithMap[T](list: List[(Int, T)]): List[T] = { 
    list.map {x => List.fill(x._1)(x._2)}.flatten
   }
  
  def decodeWithFold[T](list: List[(Int, T)]): List[T] = { 
     list.foldLeft(List[T]())((accumulated, element) => accumulated ++ List.fill(element._1)(element._2))
  }
  
  def encodeDirect[T](list: List[T]): List[(Int, T)] = {
    @tailrec
    def loop[T](result: List[(Int, T)], encodedElement: Option[(Int, T)], actual: List[T]): List[(Int, T)] = actual match {
      case Nil => if (result == Nil) Nil else result ++ encodedElement
      case y :: ys => encodedElement match {
        case None => loop(result, Some(1,y), ys)
        case Some(element) => if (y == element._2) loop(result, Some(element._1 +1,element._2), ys) else loop(result ++ encodedElement, Some(1, y), ys)
      }
    }

    loop(Nil, None, list)
  }
  
   def encodeDirectWithFold[T](list: List[T]): List[(Int, T)] = {
     list.foldLeft(List[(Int, T)]())((accumulated, element) => accumulated match {
      case head :: tail if head._2 == element => (head._1 + 1, head._2) :: tail
      case _ => (1, element) :: accumulated
    }).reverse
   }
   
   def duplicate[T](list: List[T]): List[T] = list.map(x => List.fill(2)(x)).flatten
   
   def duplicateN[T](n: Int, list: List[T]): List[T] = list.map(x => List.fill(n)(x)).flatten
   
   def drop[T](n: Int, list: List[T]): List[T] = list match {
     case Nil => throw new NoSuchElementException
     case x :: xs if list.length < n => throw new NoSuchElementException
     case _ => list.zipWithIndex.filter(x => (x._2 + 1) % n != 0).map(y => y._1)
   }
   
   // Other API options are use zipWithIndex and partition 
   def split[T](n: Int, list: List[T]): (List[T], List[T]) = (list.take(n), list.drop(n))   
   
   def slice[T](i: Int, n: Int, list: List[T]): List[T] = list.drop(i).take(n-i)
   
   // TODO: Solution from s99, think about this problem!!!
   def rotate[A](n: Int, ls: List[A]): List[A] = {
    val nBounded = if (ls.isEmpty) 0 else n % ls.length
    if (nBounded < 0) rotate(nBounded + ls.length, ls)
    else (ls drop nBounded) ::: (ls take nBounded)
  }
   
   def removeAt[T](n: Int, list: List[T]): (List[T], T) = list.splitAt(n) match {
     case (Nil, _) => throw new NoSuchElementException
     case (_, Nil) => throw new NoSuchElementException
     case (previous, element :: xs) => (previous ::: xs, element)      
   } 
}