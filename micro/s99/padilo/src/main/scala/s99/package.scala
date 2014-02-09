package object s99 {
  
  implicit def intToPrimeInt(i: Int) = new PrimeInt(i)

  implicit def intToComprimableInt(i: Int) = new ComprimableInt(i)
}