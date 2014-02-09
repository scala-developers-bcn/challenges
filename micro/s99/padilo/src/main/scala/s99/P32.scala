package s99

/*
 * Determine the greatest common divisor of two positive integer numbers.
 * Use Euclid's algorithm.
 * scala> gcd(36, 63)
 * res0: Int = 9
 */
object P32 {
	def gcd(i:Int, j:Int):Int = {
	    if(j==0) i
	    else gcd(j, i % j)
	}
}