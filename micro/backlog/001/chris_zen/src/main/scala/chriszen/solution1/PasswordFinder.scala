package chriszen.solution1

import java.io.{InputStreamReader, BufferedReader, InputStream}
import chriszen.Hash
import java.security.MessageDigest

/**
 * This is the simple solution that just calculates the hash for each dictionary key and compares with the target.
 *
 * It is more efficient than the solution2 for a single target search.
 */
class PasswordFinder {

  def findPassword(
                    targetHash: Hash,
                    inputStream: InputStream,
                    charsetName: String = "UTF-8",
                    hashAlgorithm: String = "SHA-256"
                    ) = {

    val bis = new BufferedReader(new InputStreamReader(inputStream, charsetName))

    var password: Option[String] = None

    def check(key: String) = {
      //println(s"Checking $key ...")

      val validationHash = new Hash(MessageDigest.getInstance(hashAlgorithm).digest(key.getBytes))

      if (targetHash equals validationHash)
        Some(key)
      else
        None
    }

    var s: String = null
    while (password.isEmpty && {s = bis.readLine(); s != null})
      password = check(s)

    password
  }
}
