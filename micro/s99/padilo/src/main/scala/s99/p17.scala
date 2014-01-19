package s99

/*
 * Split a list into two parts.
 * The length of the first part is given. Use a Tuple for your result.
 * Example:
 * 
 * scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
 * res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
 */
object p17 {
  def main(args: Array[String]) = {
    println(split2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
  
  // recursive
  def split[T](n: Int, l: List[T]): (List[T], List[T]) = {
    if(n==0){
      (List(), l)
    }else{
      val result = split(n-1, l.tail)
      
      (l.head::result._1, result._2)
    }
  }

  // tail recursive
  def split2[T](n: Int, l: List[T]): (List[T], List[T]) = {
	  def splitTR[T](n: Int, l: List[T], result: (List[T], List[T])): (List[T], List[T]) = {
	    if(n==0){
	      (result._1.reverse, l)
	    }else{
	      splitTR(n-1, l.tail, (l.head::result._1, List()))
	    }
	  }
	  splitTR(n, l, (List(), List()))
  }
  
  // directly
  def split3[T](n: Int, l: List[T]): (List[T], List[T]) = {
    l.splitAt(n)
  }
}