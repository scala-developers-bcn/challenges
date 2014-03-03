package s99

import s99.Arithmetic._

object dummy {

  println(315.primeFactors)                       //> List(3, 3, 5, 7)
  
  List(3,3,4,5).groupBy(f => f).map(fl => (fl._1, (fl._1, fl._2.size))).values.toList.sortWith(_._1 < _._1)
                                                  //> res0: List[(Int, Int)] = List((3,2), (4,1), (5,1))
}