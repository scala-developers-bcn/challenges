package p01_p10

object Flatten {
  def flatten(list: List[Any]): List[Any] = list match {
    case Nil => Nil
    case elem :: tail => 
      (elem match {
        case newList: List[Any] => flatten(newList)
        case default => List(default)
      }) ::: (tail match {
        case newList: List[Any] => flatten(newList)
        case default => List(default)
      })
  }
}

