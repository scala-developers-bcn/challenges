package chriszen.solution2

import java.io.{InputStreamReader, BufferedReader, InputStream}
import scala.util.Random
import chriszen.Hash

/**
 * This solution uses a Ternary Search Tree to save the pre-calculated digests for all the substrings already found.
 *
 * This would be a good solution in case that many target hashes were checked
 * but for a single one the immutability (and its associated heap use) has a big penalty
 */

class PasswordFinder {

  //TODO HashedTST does not support defining the hash algorithm yet

  def findPassword(
                  targetHash: Hash,
                  inputStream: InputStream,
                  charsetName: String = "UTF-8",
                  hashAlgorithm: String = "SHA-256"
                  ) = {

    val bis = new BufferedReader(new InputStreamReader(inputStream, charsetName))

    var password: Option[String] = None

    var tree = HashedTST.Empty

    def check(key: String) = {
      //println(s"Putting $key ...")
      val (nextTree, foundHash) = tree.put(key)
      //println(tree.toString)
      //println(buffer.toList)

      tree = nextTree

      /*println(s"$key = " + foundHash.toString)
      val validationHash = new Hash(MessageDigest.getInstance("SHA-256").digest(key.getBytes))
      if (!(validationHash equals foundHash))
        println(s"[----------- $x ]")*/

      if (targetHash equals foundHash)
        Some(key)
      else
        None
    }

    var s: String = null
    while (password.isEmpty && {s = bis.readLine(); s != null})
      password = check(s)

    //println(tree.toString)

    password
  }

  def findPasswordWithRandomization(
                                     targetHash: Hash,
                                     inputStream: InputStream,
                                     charsetName: String = "UTF-8",
                                     hashAlgorithm: String = "SHA-256",
                                     bufSize: Int = 1000) = {

    val bis = new BufferedReader(new InputStreamReader(inputStream, charsetName))

    // we use a shuffle buffer to keep the tree as balanced as possible
    val buffer = new Array[String](bufSize)
    var bufIndex: Int = 0
    val r = new Random

    var password: Option[String] = None

    var tree = HashedTST.Empty

    def check(key: String) = {
      //println(s"Putting $key ...")
      val (nextTree, foundHash) = tree.put(key)
      //println(tree.toString)
      //println(buffer.toList)

      tree = nextTree

      /*println(s"$key = " + foundHash.toString)
      val validationHash = new Hash(MessageDigest.getInstance("SHA-256").digest(key.getBytes))
      if (!(validationHash equals foundHash))
        println(s"[----------- $x ]")*/

      if (targetHash equals foundHash)
        Some(key)
      else
        None
    }

    // fill the buffer
    var s: String = null
    while ({s = bis.readLine(); s != null && bufIndex < bufSize}) {
      buffer(bufIndex) = s
      bufIndex += 1
    }
    bufIndex -= 1

    // grow the tree while keeping the buffer filled
    while (password.isEmpty && {s = bis.readLine(); s != null}) {
      val nextIndex = r.nextInt(bufIndex + 1)
      val nextWord = buffer(nextIndex)
      buffer(nextIndex) = buffer(bufIndex)
      buffer(bufIndex) = s

      password = check(nextWord)
    }

    // flush the buffer
    while (password.isEmpty && bufIndex > 0) {
      val nextIndex = r.nextInt(bufIndex + 1)
      val nextWord = buffer(nextIndex)
      buffer(nextIndex) = buffer(bufIndex)
      buffer(bufIndex) = s
      bufIndex -= 1

      password = check(nextWord)
    }

    // pop the last element
    if (password.isEmpty && bufIndex >= 0)
      password = check(buffer(0))

    //println(tree.toString)

    password
  }
}
