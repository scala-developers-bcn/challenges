package p01_p10

object Encode {
  import p01_p10.Pack._

  def encode[A](list: List[A]): List[(Int, A)] = {
    pack(list) map {
      group => (group.length, group.head)
    }
  }
}  

