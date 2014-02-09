package s99

import org.scalatest.FunSuite

/**
 * Created by ignasi on 09/02/14.
 */
class ArithmeticSuite extends FunSuite {


  import s99.Arithmetic._

  test("Determine whether a given integer number is prime.") {
    assert{ 7.isPrime  == true}
  }

  test("Determine the greatest common divisor of two positive integer numbers. Using Euclid alg."){
    assert(gcd(36, 63)==9)
  }

  test("Determine whether two positive integer numbers are coprime. Two numbers are coprime if their greatest common divisor equals 1."){
    assert(35.isCoprimeTo(64) == true)
  }

}
