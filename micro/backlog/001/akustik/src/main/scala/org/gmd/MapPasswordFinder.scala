package org.gmd

import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.BufferedReader
import javax.xml.bind.DatatypeConverter
import java.security.MessageDigest
import scala.collection.mutable
import scala.io.Source
import java.io.InputStream

class MapPasswordFinder extends PasswordFinder {

  override def findMatchesInDictionary(dictionaryPath: String, hashes: List[String]): Set[(String, Option[String])] = {
    val dict = toMap(dictionaryPath)
    hashes.map(hash => (hash, dict get hash)).toSet
  }

  def toMap(dictionaryPath: String) = {
    using(new FileInputStream(dictionaryPath)) {
      Source.fromInputStream(_).getLines().map(pwd => (computeHash(pwd), pwd)).toMap
    }
  }
}