package chriszen

import java.io.FileInputStream

object Main {
  val hashAlgorithm = "SHA-256"
  val charsetName = "UTF-8"

  def main(args: Array[String]) {

    val defaultTargetHash = "tMWuKulh/ojRKCG9+UWxVILvAhcD4fkAzL4aJ/It8H8="

    val targetHash = if (args.length > 0) args(0) else defaultTargetHash

    val inputStream = if (args.length > 1)
      new FileInputStream(args(1))
    else
      getClass.getResourceAsStream("/cain.txt")

    val passwordFinder = new solution1.PasswordFinder()
    val password = passwordFinder.findPassword(
      Hash.fromBase64(targetHash), inputStream, charsetName, hashAlgorithm)

    println(password.fold(s"The password for '$targetHash' has not been found :-(")(password => s"The password for '$targetHash' is $password"))
  }
}
