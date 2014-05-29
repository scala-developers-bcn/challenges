package chriszen.multihash

import java.io.{InputStreamReader, BufferedReader, InputStream}
import java.security.MessageDigest
import scala.collection.mutable
import javax.xml.bind.DatatypeConverter

class MapPasswordFinder extends PasswordFinder {

  private val map = new mutable.HashMap[String, String]()
  
  override def loadDictionary(inputStream: InputStream,
                     charsetName: String = "UTF-8",
                     hashAlgorithm: String = "SHA-256") {

    val bis = new BufferedReader(new InputStreamReader(inputStream, charsetName))

    var s: String = null
    while ({ s = bis.readLine(); s != null }) {
      val hash = DatatypeConverter.printBase64Binary(
        MessageDigest.getInstance(hashAlgorithm).digest(s.getBytes))
      map.put(hash, s)
    }
  }
  
  override def findPassword(hash: String): Option[String] = map.get(hash)
}
