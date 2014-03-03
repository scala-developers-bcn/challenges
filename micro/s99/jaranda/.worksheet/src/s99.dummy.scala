package s99

import s99.Arithmetic._

object dummy {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(81); 

  println(315.primeFactors);$skip(111); val res$0 = 
  
  List(3,3,4,5).groupBy(f => f).map(fl => (fl._1, (fl._1, fl._2.size))).values.toList.sortWith(_._1 < _._1);System.out.println("""res0: List[(Int, Int)] = """ + $show(res$0))}
}
