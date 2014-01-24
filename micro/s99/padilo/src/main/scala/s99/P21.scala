package s99

import scala.annotation.tailrec
/*
 * Insert an element at a given position into a list.
 * Example:
 * scala> insertAt('new, 1, List('a, 'b, 'c, 'd))
 * res0: List[Symbol] = List('a, 'new, 'b, 'c, 'd)
 */
object P21 {
  def main(args: Array[String]) = {
    def test(name: String, f: (Symbol, Int, List[Symbol]) => List[Symbol]) = {
      val l = List('a, 'b, 'c, 'd)

      println(name)
      println(f('new, 0, List('a, 'b, 'c, 'd)))
      println(f('new, 1, List('a, 'b, 'c, 'd)))
      println(f('new, 4, List('a, 'b, 'c, 'd)))
      println
    }

    test("insertAt", insertAt)
    test("insertAt2", insertAt2)
    test("insertAt3", insertAt3)
    test("insertAt4", insertAt4)
  }

  // recursive
  def insertAt[T](e: T, pos: Int, l: List[T]): List[T] = {
    if (pos == 0) {
      e :: l
    } else if (l.isEmpty) {
      throw new NoSuchElementException
    } else {
      l.head :: insertAt(e, pos - 1, l.tail)
    }
  }

  // tail recursive
  def insertAt2[T](e: T, pos: Int, l: List[T]): List[T] = {
    @tailrec
    def insertAtTR[T](e: T, pos: Int, l: List[T], result: List[T]): List[T] = {
      if (pos == 0) {
        result.reverse ::: (e :: l)
      } else if (l.isEmpty) {
        throw new NoSuchElementException
      } else {
        insertAtTR(e, pos - 1, l.tail, l.head :: result)
      }
    }

    insertAtTR(e, pos, l, List())
  }

  // functional
  def insertAt3[T](e: T, pos: Int, l: List[T]): List[T] = {
    l.take(pos) ::: e :: l.drop(pos)
  }

  // Split solution provided by Phil
  def insertAt4[A](e: A, n: Int, ls: List[A]): List[A] = {
    ls.splitAt(n) match {
      case (pre, post) => pre ::: e :: post
    }
  }

}