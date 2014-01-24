package s99

import scala.util.Random
import scala.annotation.tailrec

/*
 * Extract a given number of randomly selected elements from a list.
 * Example:
 * scala> randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
 * res0: List[Symbol] = List('e, 'd, 'a)
 */
object p23 {
  def main(args: Array[String]) {
    println(randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h)))
    println(randomSelect2(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h)))
  }

  def randomSelect[T](n: Int, l: List[T]): List[T] = {
    if (n <= 0 || l.isEmpty) {
      List()
    } else {
      val randN = Random.nextInt(l.length)
      val (resultList:List[T], elem) = p20.removeAt(randN, l)
      elem :: randomSelect(n - 1, resultList)
    }
  }

  def randomSelect2[T](n: Int, l: List[T]): List[T] = {
    @tailrec
    def randomSelectTR(n: Int, l: List[T], result: List[T]): List[T] = {
      if (n <= 0 || l.isEmpty) {
        result
      } else {
        val randN = Random.nextInt(l.length)
        val (resultList:List[T], elem) = p20.removeAt(randN, l)
        randomSelectTR(n - 1, resultList, elem::result)
      }
    }
    randomSelectTR(n, l, List())
  }

}