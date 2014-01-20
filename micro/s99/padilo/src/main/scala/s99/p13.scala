package s99

/*
 * Run-length encoding of a list (direct solution).
 * Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods you've written (like P09's pack); do all the work directly.
 * Example:
 * 
 * scala> encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
 * res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
 */
object p13 {
	def main(args: Array[String]) = {
	  println(encodeDirect2(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
	}
	
	// directly usign tail recursion
	def encodeDirect[T](l:List[T]):List[(Int, T)] = {
	  def encodeDirectTR[T](l:List[T], result:List[(Int, T)]):List[(Int, T)] = {
		  (l,result) match {
		    case (lHead::lTail, rHead::rTail) if (lHead==rHead._2) => encodeDirectTR(lTail, (rHead._1+1, rHead._2)::rTail)
		    case (lHead::lTail, _) => encodeDirectTR(lTail, (1, lHead)::result)
		    case _ => result
		  }
	  }
	  
	  encodeDirectTR(l, List())
	}
	
	// Using span
	def encodeDirect2[T](l:List[T]):List[(Int, T)] = {
	  def encodeDirectTR[T](l:List[T], result:List[(Int, T)]):List[(Int, T)] = {
	    l match {
	      case h::_ => {
	        val spplited = l.span(_==h)
	        
	        encodeDirectTR(spplited._2, (spplited._1.length, h)::result)
	      }
	      case _ => result
	    }
	  }
	  
	  encodeDirectTR(l, List())
	}
	
	
}