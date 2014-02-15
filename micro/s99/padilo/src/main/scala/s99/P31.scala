package s99

import com.sun.org.apache.xml.internal.serializer.ToStream

/*
 * Determine whether a given integer number is prime.
 * scala> 7.isPrime
 * res0: Boolean = true
 */

class PrimeInt(n: Int) {
  def isPrime = {
    (n > 1 || n < 0) && (2 until n).forall(n % _ != 0)
  }
}
