package s99

object Lists {

  def last(in: List[Int]) : Int = in.reverse.head

  def penultimate(in:List[Int]) : Int = in.reverse.tail.head

  def nth(index: Int, xs:List[Int]) : Int = index match {
    case 0 => xs.head
    case _ => nth(index-1, xs.tail)
  } 

}
