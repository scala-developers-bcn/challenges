package s99

import scala.language.implicitConversions
/*
 * Calculate Euler's totient function phi(m).
 * Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m) that are coprime to m.
 * scala> 10.totient
 * res0: Int = 4
 */

class EulerTotientInt(m: Int) {
  def totient = {
    def comprimes = for {
      r <- 1 to m
      if r.isCoprimeTo(m)
    } yield r
    
    comprimes.length
  }
}