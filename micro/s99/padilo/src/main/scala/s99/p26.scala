package s99

import scala.annotation.tailrec

/*
 * Generate the combinations of K distinct objects chosen from the N elements of a list.
 * In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). For pure mathematicians, this result may be great. But we want to really generate all the possibilities.
 * Example:
 * 
 * scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
 * res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
*/
object p26 {
  def main(args: Array[String]) {
    val test = List('a, 'b, 'c, 'd, 'e, 'f)

    val result = combinations1(3, test)
    println(result)
    println(result.length == 20)

  }

  def combinations1(n: Int, l: List[Symbol]): List[List[Symbol]] = {

    def rCombinations(i: Int, l: List[Symbol], comb: List[Symbol], result: List[List[Symbol]]): List[List[Symbol]] = {
      if (i == 0) {
        comb.reverse :: result
      } else {
        l match {
          case head :: tail => {
            rCombinations(i - 1, tail, head :: comb, rCombinations(i, tail, comb, result))
          }
          case _ => {
            result
          }
        }
      }
    }
    rCombinations(n, l, Nil, Nil)
  }

}