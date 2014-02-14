package s99

import Stream._

/*
 * Determine the prime factors of a given positive integer.
 * Construct a flat list containing the prime factors in ascending order.
 * scala> 315.primeFactors
 * res0: List[Int] = List(3, 3, 5, 7)
 */
class PrimerFactorInt(n: Int) {
  def primes: Stream[Int] = from(1).filter(_.isPrime)

  def primerFactors: List[Int] = {
    unOrderedPrimeFactors.sortWith(_ < _)
  }

  private def unOrderedPrimeFactors = {
    def rPrimerFactors(n: Int, l: List[Int]): List[Int] = {
      if (n == 1) {
        l
      } else {
        def divide = primes.filter(n % _ == 0).head

        println(n / divide)
        rPrimerFactors(n / divide, divide :: l)
      }
    }
    
    rPrimerFactors(n, Nil)
  }
}