package s99

import org.scalatest.FunSuite

class ArithmeticSpec extends FunSuite {
  
  import s99.Arithmetic._;
  
  test("Determine whether a given integer number is prime.") {
    assert{ 7.isPrime == true } 
  }
  
  test("Determine the greatest common divisor of two positive integer numbers.") {
	  assert{ gcd(36, 63) == 9 }
  }
  
  test("Determine whether two positive integer numbers are coprime.") {
    assert{ 35.isCoprimeTo(64) }
  }
  
  test("Determine the prime factors of a given positive integer.") {
    assert{ 315.primeFactors == List(3, 3, 5, 7) }
  }
  
  test("Determine the prime factors of a given positive integer (2).") {
    assert{ 315.primeFactorMultiplicity == List((3,2), (5,1), (7,1)) }
  }
  
}