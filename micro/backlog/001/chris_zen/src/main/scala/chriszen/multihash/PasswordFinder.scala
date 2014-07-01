package chriszen.multihash

import java.io.InputStream

abstract class PasswordFinder {

  def loadDictionary(inputStream: InputStream,
                     charsetName: String = "UTF-8",
                     hashAlgorithm: String = "SHA-256")

  def findPassword(hash: String): Option[String]
}
