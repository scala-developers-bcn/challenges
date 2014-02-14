package object s99 {
  import scala.language.implicitConversions

  implicit def intToPrimeInt(i: Int) = new PrimeInt(i)

  implicit def intToComprimableInt(i: Int) = new ComprimableInt(i)

  implicit def intToEulerTotient(i: Int) = new EulerTotientInt(i)

  implicit def intToPrimeFactor(i: Int) = new PrimerFactorInt(i)
}