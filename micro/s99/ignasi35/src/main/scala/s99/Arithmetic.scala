package s99

object Arithmetic {

  class MyInt(val i: Int) {
    def isPrime = {
      val r = (2 to (math.sqrt(i).toInt + 1))
      r.filter {
        x => i % x == 0
      }.length == 0
    }
  }

  implicit def intToMyInt(i: Int) = new MyInt(i)
}
