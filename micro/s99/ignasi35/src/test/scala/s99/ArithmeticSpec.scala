package s99

import org.scalatest.FunSuite

/**
 * Created by ignasi on 09/02/14.
 */
class ArithmeticSuite extends FunSuite {


  import s99.Arithmetic._

  test("S99 can determine whether a given integer number is prime.") {
    assert{ 7.isPrime  == true}
  }

  test("s99 can determine the greatest common divisor of two positive integer numbers. Using Euclid alg."){
    assert(gcd(36, 63)==9)
  }
  /*


scala> 7.isPrime
res0: Boolean = true

res0: Int = 9
P33 (*) Determine whether two positive integer numbers are coprime.
Two numbers are coprime if their greatest common divisor equals 1.
scala> 35.isCoprimeTo(64)
res0: Boolean = true

   */
}
