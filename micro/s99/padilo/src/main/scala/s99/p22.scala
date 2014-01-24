package s99

import scala.annotation.tailrec

/*
 * Create a list containing all integers within a given range.inclusi
 * Example:
 * scala> range(4, 9)
 * res0: List[Int] = List(4, 5, 6, 7, 8, 9)
 */
object p22 {
  def main(args: Array[String]) = {
    def test(name: String, f: (Int, Int) => List[Int]) = {

      println(name)
      println(f(4, 9))
      println
    }

    test("range", range)
    test("range2", range2)
    test("range3", range3)
    test("rangeFunctional", rangeFunctional)
    test("rangeBuiltin", rangeBuiltin)
  }

  def range(i: Int, j: Int): List[Int] = {
    (i to j).toList
  }

  def range2(i: Int, j: Int): List[Int] = {
    if (i > j) List()
    else i :: range2(i + 1, j)
  }

  // Easy
  def range3(i: Int, j: Int): List[Int] = {
    @tailrec
    def range3TR(i: Int, j: Int, result: List[Int]): List[Int] = {
      if (i > j) result
      else range3TR(i, j - 1, j :: result)
    }

    range3TR(i, j, List())
  }

  // Phil solutions
  def rangeBuiltin(start: Int, end: Int): List[Int] = {
    List.range(start, end + 1)
  }

  // The classic functional approach would be to use `unfoldr`, which Scala
  // doesn't have.  So we'll write one and then use it.
  def unfoldRight[A, B](s: B)(f: B => Option[(A, B)]): List[A] =
    f(s) match {
      case None => Nil
      case Some((r, n)) => r :: unfoldRight(n)(f)
    }
  def rangeFunctional(start: Int, end: Int): List[Int] =
    unfoldRight(start) { n =>
      if (n > end) None
      else Some((n, n + 1))
    }

}