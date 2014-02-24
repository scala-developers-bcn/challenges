package org.scalabcn.s99

import scala.annotation.tailrec

object ArithmeticExtensions {

  implicit class S99Int(x: Int) {

  	val primes: Stream[Int] = 2 #:: 3 #:: 5 #:: 7 #:: 11 #:: 13 #:: Stream.from(17, 2).filter(_.isPrime)

    def isPrime(): Boolean = {     
      x > 0 && (x == 1 || primes.takeWhile(_ <= Math.sqrt(x)).forall(x % _ != 0))
    }

    def isCoprimeTo(y: Int): Boolean = gcd(x, y) == 1

    //No need to use a list, could be done directly with the sequence
    def totient: Int = (1 to x).toList.filter(_.isCoprimeTo(x)).size

    def primeFactors: List[Int] = {
    	@tailrec
    	def primeFactorsR(n: Int, ps: Stream[Int], factors: List[Int]): List[Int] = {
    		if(n.isPrime) n :: factors
    		else if(n % ps.head == 0) primeFactorsR(n / ps.head, ps, ps.head :: factors)
    		else primeFactorsR(n, ps.tail, factors) 
    	}

    	primeFactorsR(x, primes, Nil).reverse
    }

    //can be used encode instead of groupBy
    def primeFactorMultiplicity(): List[(Int, Int)] = {
    	val l = x.primeFactors.groupBy(f => f).map(fl => (fl._1, (fl._1, fl._2.size))).values.toList
    	l.sortWith(_._1 < _._1)
    }

    def totient2: Int = x.primeFactorMultiplicity.foldLeft(1) { (r, f) =>
    	f match { case (p, m) => r * (p - 1) * Math.pow(p, m - 1).toInt }
  	}
	
  }

  def gcd(m: Int, n: Int): Int = if (n == 0) m else gcd(n, m % n)
}

