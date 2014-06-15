package org.gmd

import javax.xml.bind.DatatypeConverter
import java.security.MessageDigest
import java.io.InputStream
import scala.io.Codec

trait PasswordFinder extends InOutOps {
  def findMatchesInDictionary(dictionaryPath: String, hashes: List[String]): Set[(String, Option[String])]

  def computeHash(pwd: String): String = {
    DatatypeConverter.printBase64Binary(MessageDigest.getInstance("SHA-256").digest(pwd.getBytes))
  }
}