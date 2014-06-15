package org.gmd

import java.io.FileInputStream
import scala.io.Source

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