package s99

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import scala.math.pow

class ListsTest extends FlatSpec with ShouldMatchers {
  "P26 combinations" should "return correctly the example" in {
    def test = List('a, 'b, 'c, 'd, 'e, 'f)
    def result = P26.combinations(3, test)
    def theExpected = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), List('a, 'b, 'f), List('a, 'c, 'd), List('a, 'c, 'e), List('a, 'c, 'f), List('a, 'd, 'e), List('a, 'd, 'f), List('a, 'e, 'f), List('b, 'c, 'd), List('b, 'c, 'e), List('b, 'c, 'f), List('b, 'd, 'e), List('b, 'd, 'f), List('b, 'e, 'f), List('c, 'd, 'e), List('c, 'd, 'f), List('c, 'e, 'f), List('d, 'e, 'f))

    result should be(theExpected)
  }
  it should "return groups of 2 elements if n=2" in {
    def test = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    def result = P26.combinations(2, test)

    result.forall(_.length == 2) should be(true)
  }
  it should "work with List of 1 element" in {
    def test = List('a)
    def result = P26.combinations(1, test)
    def theExpected = List(List('a))

    result should be(theExpected)
  }

  "P27.group3(..)" should "return only a group with L(L(a),L(b),L(c)) with 3 elements L(a,b,c)" in {
    def test = List("a", "b", "c")
    def result = P27.group3(test)
    def theExpected = List(List(List("a"), List("b"), List("c")))

    result should be(theExpected)
  }

  it should "work correctly with 4 elements L(a,b,c,d)" in {
    def test = List('a, 'b, 'c, 'd)
    def result = P27.group3(test)

    // It will be nice to order this list, and also the result list to compare because this result is based on my implementation... UGLY
    def theExpected = List(
      List(List('a), List('b), List('c, 'd)),
      List(List('a), List('b, 'c), List('d)),
      List(List('a), List('b, 'd), List('c)),
      List(List('a, 'b), List('c), List('d)),
      List(List('a, 'c), List('b), List('d)),
      List(List('a, 'd), List('b), List('c)))

    result should be(theExpected)
  }

  "P27.groupN(..)" should "return only one possibility with groups of 1 person if the group size = list length" in {
    def test = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)
    val result = P27.groupN(test, test.length)

    result.length should be(1)
    result(0).length should be(test.length)
  }

  it should "work with elements repited" in {
    def test = List(1, 1, 1)
    val result = P27.groupN(test, test.length)

    result.length should be(1)
    result(0).length should be(test.length)
  }

  "P27.group(...)" should "return only a group with L(L(a),L(b),L(c)) with 3 elements L(a,b,c) and requested groups List(1,1,1)" in {
    def test = List("a", "b", "c")
    def result = P27.group(List(1, 1, 1), test)
    def theExpected = List(List(List("a"), List("b"), List("c")))

    result should be(theExpected)
  }

  it should "throw exception if groups lenght != list.lenght" in {
    def test = List('a, 'b, 'c, 'd)
    intercept[IllegalArgumentException] {
      val result = P27.group(List(1, 1, 3), test)
    }
  }

  it should "throw exception if lenght of any group < 1" in {
    def test = List('a, 'b, 'c, 'd)
    intercept[IllegalArgumentException] {
      val result = P27.group(List(0, 1, 3), test)
    }
  }

  it should "work correctly with test1" in {
    def test = List('a, 'b, 'c, 'd)
    def result = P27.group(List(1, 1, 2), test)

    // It will be nice to order this list, and also the result list to compare because this result is based on my implementation... UGLY
    def theExpected = List(
      List(List('a), List('b), List('c, 'd)),
      List(List('a), List('b, 'c), List('d)),
      List(List('a), List('b, 'd), List('c)),
      List(List('a, 'b), List('c), List('d)),
      List(List('a, 'c), List('b), List('d)),
      List(List('a, 'd), List('b), List('c)))

    result should be(theExpected)
  }

  it should "work correctly with test2" in {
    def test = List('a, 'b, 'c, 'd)
    def result = P27.group(List(2, 2), test)

    // It will be nice to order this list, and also the result list to compare because this result is based on my implementation... UGLY
    def theExpected = List(
      List(List('a, 'b), List('c, 'd)),
      List(List('a, 'c), List('b, 'd)),
      List(List('a, 'd), List('b, 'c)))

    result should be(theExpected)
  }

  "P28.lsort(...)" should "work correcly with test1" in {
    val test = List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))
    val theExpected = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))

    P28.lsort(test) should be(theExpected)
  }

  it should "work correcly with test2" in {
    val test = List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('m, 'n), List('i, 'j, 'k, 'l), List('d, 'e), List('o))
    val theExpected = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))

    P28.lsort(test) should be(theExpected)
  }

  it should "work correcly with List(List())" in {
    val test = List(List())
    val theExpected = List(List())

    P28.lsort(test) should be(theExpected)
  }

  "P28.lsortFreq(...)" should "work correcly with List(List())" in {
    val test = List(List())
    val theExpected = List(List())

    P28.lsortFreq(test) should be(theExpected)
  }

  it should "work correcly with test1" in {
    val test = List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))
    val theExpected = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))

    P28.lsortFreq(test) should be(theExpected)
  }

  it should "work correcly with 1 element" in {
    val test = List(List('z, 'b, 'c))

    P28.lsortFreq(test) should be(test)
  }

  it should "work with simple sublists of 1 element" in {
    val test = List(List('c), List('b), List('a))
    val theExpected = List(List('a), List('b), List('c))

    P28.lsortFreq(test) should be(theExpected)
  }

}