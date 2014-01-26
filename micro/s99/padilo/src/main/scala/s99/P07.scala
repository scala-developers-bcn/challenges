package s99

/*
 * Flatten a nested list structure.
 * Example:
 * scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
 * res0: List[Any] = List(1, 1, 2, 3, 5, 8)
 */
object P07 {
  def main(args: Array[String]): Unit = {
    val input = List(List(1, 1), 2, List(3, List(5, 8)))
    
    println(flatten(input))
    println(flatten2(input))
  }

  def flatten(l: List[Any]): List[Any] = {
    def flattenTR(elem:Any, result:List[Any]): List[Any] = {
	    elem match {
	      case Nil => result
	      case x::tail => flattenTR(x, flattenTR(tail, result))
	      case x => x::result
	    }
    }
    flattenTR(l, List())
  }

  def flatten2(l: List[Any]): List[Any] = {
    l flatMap {
      case x:List[_] => flatten2(x)
      case e => List(e)
    }
  }
}