package chriszen.solution2

import java.security.MessageDigest
import chriszen.Hash

/**
 * Immutable hashed ternary search tree.
 *
 * http://en.wikipedia.org/wiki/Ternary_search_tree
 *
 * It keeps the digest and the previously calculated hash for each node
 * so the calculation of the hash for strings with common prefixes is optimized.
 *
 * The hash algorithm is hard-coded and requires recompilation to change it.
 */

object HashedTST {
  val Algorithm = "SHA-256"
  val Empty = new HashedTST {}
}

trait HashedTST {

  def isEmpty: Boolean = true

  def put(s: String, index: Int, prevDigest: MessageDigest): (HashedTST, Option[Hash]) = {
    if (index >= s.length)
      (HashedTST.Empty, None)
    else {
      val currentChar = s.charAt(index)

      val digest = prevDigest.clone.asInstanceOf[MessageDigest]

      digest.update(currentChar.toByte)

      val (subtree, foundHash) = put(s, index + 1, digest)

      val nextHash = if (index == s.length - 1)
        Some(new Hash(digest.clone.asInstanceOf[MessageDigest].digest()))
      else None

      (new HashedTSTNode(HashedTST.Empty, subtree, HashedTST.Empty, currentChar,
                        /*s.substring(0, index + 1),*/ digest, nextHash), foundHash orElse nextHash)
    }
  }

  def put(s: String): (HashedTST, Hash) = {
    val (nextTree, foundHash) = put(s, 0, createDigest)
    (nextTree, foundHash.get)
  }

  def createDigest = { MessageDigest.getInstance(HashedTST.Algorithm) }

  def toStringHelper(sb: StringBuilder, margin: String) {
    sb.append("-\n")
  }

  override def toString: String = "Empty"
}

class HashedTSTNode(
                     val left: HashedTST,
                     val mid: HashedTST,
                     val right: HashedTST,
                     val key: Char,
                     //val path: String,
                     val digest: MessageDigest,
                     val hash: Option[Hash]
                     ) extends HashedTST {

  override def isEmpty() = false

  override def put(s: String, index: Int, prevDigest: MessageDigest): (HashedTST, Option[Hash]) = {
    if (index >= s.length())
      (HashedTST.Empty, None)
    else {
      val currentChar = s.charAt(index)

      val nextHash = if (index == s.length - 1)
        Some(hash getOrElse new Hash(digest.clone.asInstanceOf[MessageDigest].digest()))
      else None

      if (currentChar == key) {
        val (subtree, foundHash) = mid.put(s, index + 1, digest)
        (new HashedTSTNode(left, subtree, right, key, /*path,*/ digest, nextHash), foundHash orElse nextHash)
      }
      else if (currentChar < key) {
        val (subtree, foundHash) = left.put(s, index, prevDigest)
        (new HashedTSTNode(subtree, mid, right, key, /*path,*/ digest, nextHash), foundHash orElse nextHash)
      }
      else {//if (currentChar > ch)
      val (subtree, foundHash) = right.put(s, index, prevDigest)
        (new HashedTSTNode(left, mid, subtree, key, /*path,*/ digest, nextHash), foundHash orElse nextHash)
      }
    }
  }

  override def put(s: String): (HashedTST, Hash) = {
    val (nextTree, foundHash) = put(s, 0, createDigest)
    (nextTree, foundHash.get)
  }

  override def toStringHelper(sb: StringBuilder, margin: String) {
    //sb.append(s"[$key, $path")
    sb.append(s"[$key")
    sb.append(hash.fold("]")(h => ", " + h.toString + "]"))

    if (left != HashedTST.Empty || mid != HashedTST.Empty || right != HashedTST.Empty) {
      sb.append("\n")
      if (left != HashedTST.Empty) {
        sb.append(s"${margin}l: ")
        left.toStringHelper(sb, margin + "    ")
      }
      if (mid != HashedTST.Empty) {
        sb.append(s"${margin}m: ")
        mid.toStringHelper(sb, margin + "    ")
      }
      if (right != HashedTST.Empty) {
        sb.append(s"${margin}r: ")
        right.toStringHelper(sb, margin + "    ")
      }
    }
    else
      sb.append("\n")
  }

  override def toString: String = {
    val sb = new StringBuilder
    toStringHelper(sb, "")
    sb.toString()
  }
}



