package org.scalabcn.s99

import scala.annotation.tailrec

object ArithmeticExtensions {

  implicit class S99Int(x: Int) {

    def isPrime(): Boolean = {
      val primes: Stream[Int] = 2 #:: 3 #:: 5 #:: 7 #:: 11 #:: 13 #:: Stream.from(17, 2).filter(_.isPrime)
      x > 0 && (x == 1 || primes.takeWhile(_ <= Math.sqrt(x)).forall(x % _ != 0))
    }

    def isCoprimeTo(y: Int): Boolean = gcd(x, y) == 1
  }

  def gcd(m: Int, n: Int): Int = if (n == 0) m else gcd(n, m % n)
}

