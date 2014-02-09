package s99

object Arithmetic {

  class MyInt(val i: Int) {
    def isPrime = {
      val r = (2 to (math.sqrt(i).toInt + 1))
      r.filter {
        x => i % x == 0
      }.length == 0
    }

    def isCoprimeTo(j:Int):Boolean ={
      gcd(i,j)==1
    }

  }

  implicit def intToMyInt(i: Int) = new MyInt(i)


  def gcd(a: Int, b: Int): Int = {
    require(a > 0)
    require(b > 0)
    if(a!=b){
    val max = math.max(a,b)
    val min = math.min(a,b)
      gcd(max-min, min)
    }else{
      a
    }
  }

}
