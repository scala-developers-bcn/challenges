package s99
/*
 * Determine the prime factors of a given positive integer (2).
 * Construct a list containing the prime factors and their multiplicity.
 * scala> 315.primeFactorMultiplicity
 * res0: List[(Int, Int)] = List((3,2), (5,1), (7,1))
 * Alternately, use a Map for the result.
 * 
 * scala> 315.primeFactorMultiplicity
 * res0: Map[Int,Int] = Map(3 -> 2, 5 -> 1, 7 -> 1)
 */
class PrimeFactorMultiplicity(n: Int) {
  def primeFactorMultiplicity = {
    P10.encode(n.primerFactors).map((x)=>(x._2,x._1)).sortWith((x,y)=>x._1>x._2)
  }
}