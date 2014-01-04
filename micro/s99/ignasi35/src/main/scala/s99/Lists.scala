package s99

object Lists {

  def last(in: List[Int]) : Int = in.reverse.head

  def penultimate(in:List[Int]):Int = in.reverse.tail.head

}
