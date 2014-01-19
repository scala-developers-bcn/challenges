package s99

/*
 * Find the number of elements of a list.
 * Example:
 * scala> length(List(1, 1, 2, 3, 5, 8))
 * res0: Int = 6
 */

object p04 {
  def main(args: Array[String]):Unit = {
    println(length(List(1, 1, 2, 3, 5, 8)))
  }
  
  def length(l:List[_]) = {
    l.length
    //l.count(p=>true) stupid but correct alternative
  }

  def length2(l:List[_]):Int = {
    l match {
      case _::tail => length2(tail) + 1
      case _ => 0
    }
  }

  def length3(l:List[_]):Int = {
    def length3TR(l:List[_], count:Int):Int = {
      l match {
        case _::tail => length3TR(tail, count+1)
        case _ => count
      }
    }
    length3TR(l,0)
  }
	
	      

}