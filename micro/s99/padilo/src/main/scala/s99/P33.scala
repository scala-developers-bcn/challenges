package s99

/*
 * Determine whether two positive integer numbers are coprime.
 * Two numbers are coprime if their greatest common divisor equals 1.
 * scala> 35.isCoprimeTo(64)
 * res0: Boolean = true
 */

class ComprimableInt(n: Int) {
  def isCoprimeTo(i: Int) = {
    P32.gcd(n, i) == 1
  }
}