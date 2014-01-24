package s99

import scala.util.Random
/*
 * Lotto: Draw N different random numbers from the set 1..M.
 * Example:
 * scala> lotto(6, 49)
 * res0: List[Int] = List(23, 1, 17, 33, 21, 37)
 */
object p24 {
  def main(args: Array[String]) {
    println(lotto(6, 49))
    println(lotto2(6, 49))
  }
  // My solution, I win this time :)
  def lotto(n: Int, m: Int) = {
    List.fill(n)(Random.nextInt(m)+1)    
  }
  
  // Phil's solution
  def lotto2(count: Int, max: Int): List[Int] = 
    p23.randomSelect(count, List.range(1, max + 1))
}