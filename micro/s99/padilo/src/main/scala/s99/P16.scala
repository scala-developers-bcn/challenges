package s99

/*
 * Drop every Nth element from a list.
 * Example:
 * scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
 * res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
 */
object P16 {
  def main(args: Array[String]) = {
    println(drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
    println(droP2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  // recursive
  def drop[T](n: Int, l: List[T]): List[T] = {
   def dropR[T](i: Int, l: List[T]): List[T] = {
     (i,l) match {
       case (1,_::t) => dropR(n, t)
       case (_,h::t) => h::dropR(i-1, t)
       case _ => List()
     }
   }
   dropR(n, l)
  }

  // tail recursive
  def droP2[T](n: Int, l: List[T]): List[T] = {
   def dropTR[T](i: Int, l: List[T], result:List[T]): List[T] = {
     (i,l) match {
       case (1,_::t) => dropTR(n, t, result)
       case (_,h::t) => dropTR(i-1, t, h::result)
       case _ => result
     }
   }
   dropTR(n, l, List())
  }

  // Functional provided by Phil, awesome solution but inefficient
  def dropFunctional[A](n: Int, ls: List[A]): List[A] = {
    /*
     * zipWithIndex creates a List[(A,Int)] where _2 is the index number
     * Then the filter filters every Nth number
     * After this we have the solution in a List[(A,Int)]
     * Map to return the List of items
     */  
    ls.zipWithIndex filter { v => (v._2 + 1) % n != 0 } map { _._1 }
  }
  
}