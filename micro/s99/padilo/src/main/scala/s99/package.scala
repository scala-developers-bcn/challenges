package object s99 {
  implicit def intToPrimeInt(i: Int):PrimeInt = {
    new PrimeInt(i)
  }

}