package s99

import scala.annotation.tailrec

object Arithmetic {
  
  implicit def int2MyInt(i: Int) = new MyInt(i);
  
  val primes: Stream[Int] = 2 #:: 3 #:: 5 #:: 7 #:: 11 #:: 13 #:: Stream.from(17).filter(_.isPrime)
	
  class MyInt(val i: Int) {
  
    def isPrime: Boolean = {
	  	(2 to math.sqrt(i).toInt).filter(n => i % n == 0).length == 0
  	}
    
    def isCoprimeTo(n: Int): Boolean = {
      gcd(i, n) == 1
    }
    
    def totient: Int = {
      (1 to i).filter(i.isCoprimeTo(_)).length
    }
    
    def totientImproved : Int = {
      i.primeFactorMultiplicity.map( n => (n._1 - 1) * math.pow((n._1),(n._2 - 1)) ).reduce( (n , m) => n * m).intValue();
    }
    
    def primeFactors: List[Int] = {
      @tailrec
      def primeFactorsRec(n: Int, ps: Stream[Int], factors: List[Int]) : List[Int] = {
        if (n.isPrime) n :: factors
        else if (n % ps.head == 0) primeFactorsRec(n / ps.head, ps, ps.head :: factors)
        else primeFactorsRec(n, ps.tail, factors)
      }
      primeFactorsRec(i, primes, Nil).reverse
    }
    
    def primeFactorMultiplicity: List[(Int,Int)] = {
      val factorsMultiplicity = i.primeFactors.groupBy(f => f).map(f1 => (f1, (f1._1, f1._2.size))).values.toList
      factorsMultiplicity.sortWith(_._1 < _._1)
    }
  
  }
  
  def gcd(a: Int, b: Int) : Int = (a,b) match {
    case (a, 0) => a
    case _ => gcd(b, a%b)
  }
  
  def listPrimesInRange(r: Range) : List[Int] = {
    primes.dropWhile(_ < r.head).takeWhile(_ <= r.last).toList
  }
  
}

