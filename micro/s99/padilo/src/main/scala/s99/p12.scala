package s99

/*
 * Decode a run-length encoded list.
 * Given a run-length code list generated as specified in problem P10, construct its uncompressed version.
 * Example:
 * 
 * scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
 * res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
 */
object p12 {
  def main(args: Array[String]): Unit = {
    val l = List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))
    val t1 = System.currentTimeMillis()
    println(decode3(l))
    val t2 = System.currentTimeMillis()
    
    println("Time decode:" + (t2-t1))
  }

  // First solution
  def decode[T](l: List[Pair[Int, T]]): List[T] = {
    def decodeTR(l: List[Pair[Int, T]], result: List[T]): List[T] = {
      l match {
        case (n, s) :: tail => decodeTR(tail, result:::List.fill(n)(s)); 
        case Nil => result
      }
    }
    decodeTR(l, List())
  }
  
  // Another solution
  def decode2[T](l: List[Pair[Int, T]]): List[T] = {
    def decode2TR(l: List[Pair[Int, T]], result: List[T]): List[T] = {
      l match {
        case (1, s) :: tail => decode2TR(tail, s::result); 
        case (n, s) :: tail => decode2TR((n-1,s)::tail, s::result); 
        case Nil => result
      }
    }
    decode2TR(l, List())
  }

  // Flatmap solution, much better (provided by Phil Gold)
  def decode3[T](l: List[Pair[Int, T]]): List[T] = {
    l flatMap {
      e=>List.fill (e._1) (e._2)
  	}
  }
}