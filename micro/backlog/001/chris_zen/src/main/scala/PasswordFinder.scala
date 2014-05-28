import java.io.InputStream

class PasswordFinder(val inputStream: InputStream) {


}

object Main {
  def main(args: Array[String]) {
    val inputStream = getClass().getResourceAsStream("/cain_test.txt")

    //val pf = new PasswordFnder(inputStream)
    println(TST.fromInputStream(inputStream).toString)
  }
}