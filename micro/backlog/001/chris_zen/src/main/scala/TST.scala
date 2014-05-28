/**
 * Immutable Ternary Search Tree
 *
 * Created by cperez on 28/05/14.
 */

import java.io.{InputStreamReader, BufferedReader, InputStream}
import java.security.MessageDigest
import scala.util.Random

trait TernarySearchTree {

  def isEmpty(): Boolean = true

  def put(s: String, index: Int): TernarySearchTree = {
    if (index >= s.length())
      TST.Empty
    else
      new TST(
        TST.Empty, put(s, index + 1), TST.Empty,
        s.charAt(index), index == s.length() - 1)
  }

  def put(s: String): TernarySearchTree = {
    put(s, 0)
  }

  def toString(sb: StringBuilder, margin: String) {
    sb.append("-\n")
  }

  override def toString: String = "Empty"
}

object TST {

  val Empty = new TernarySearchTree {}

  def fromInputStream(is: InputStream, charsetName: String = "UTF-8") = {
    val bis = new BufferedReader(new InputStreamReader(is, charsetName))

    var tree = Empty

    // suffle buffer
    val bufSize = 1000
    val buffer = new Array[String](bufSize)
    var bufIndex: Int = 0
    val r = new Random

    // fill the buffer
    var s: String = null
    while ({s = bis.readLine(); s != null && bufIndex < bufSize}) {
      buffer(bufIndex) = s
      bufIndex += 1
    }
    bufIndex -= 1

    // grow tree while keeping the buffer filled
    while ({s = bis.readLine(); s != null}) {
      val nextIndex = r.nextInt(bufIndex + 1)
      val nextWord = buffer(nextIndex)
      buffer(nextIndex) = buffer(bufIndex)
      buffer(bufIndex) = s

      //println(s"1:Putting $nextWord ...")
      tree = tree.put(nextWord)
      //println(tree.toString)
      //println(buffer.toList)
    }

    // flush the buffer
    while (bufIndex > 0) {
      val nextIndex = r.nextInt(bufIndex + 1)
      val nextWord = buffer(nextIndex)
      buffer(nextIndex) = buffer(bufIndex)
      buffer(bufIndex) = s
      bufIndex -= 1

      //println(s"2:Putting $nextWord ...")
      tree = tree.put(nextWord)
      //println(tree.toString)
      //println((for (i <- 0 to bufIndex) yield buffer(i)).toList)
    }

    // pop the last element
    if (bufIndex >= 0) {
      val v = buffer(0)
      println(s"3:Putting $v ...")
      tree = tree.put(v)
      //println(tree.toString)
    }

    tree
  }
}

/*class HashDigest(prev: HashDigest, ch: Int) {
  val m: MessageDigest = prev.clone()
  m.update(ch)

  override def equals(other: Any): Boolean = {
    if (!other.isInstanceOf[HashDigest])
      false
    else {
      val o = other.asInstanceOf[HashDigest]
      if (o.bytes.size != bytes.size)
        false
      else {
        var i = 0;
        while (i < bytes.size && bytes(i) == o(i))
          i += 1
        i == bytes.size
      }
    }
  }
}*/

class TST(
           val left: TernarySearchTree,
           val mid: TernarySearchTree,
           val right: TernarySearchTree,
           val key: Int,
           val word: Boolean
           //val hash: Option[HashDigest]
           ) extends TernarySearchTree {

  override def isEmpty() = false

  override def put(s: String, index: Int): TernarySearchTree = {
    if (index >= s.length())
      TST.Empty
    else {
      val currentChar = s.charAt(index)
      if (currentChar == key)
        new TST(
          left, mid.put(s, index + 1), right,
          key, word || index == s.length() - 1)
      else if (currentChar < key)
        new TST(
          left.put(s, index), mid, right,
          key, word || index == s.length() - 1)
      else //if (currentChar > ch)
        new TST(
          left, mid, right.put(s, index),
          key, word || index == s.length() - 1)
    }
  }

  override def put(s: String): TernarySearchTree = {
    put(s, 0)
  }

  override def toString(sb: StringBuilder, margin: String) {
    sb.append(s"[$key, $word]")
    if (left != TST.Empty || mid != TST.Empty || right != TST.Empty) {
      sb.append("\n")
      if (left != TST.Empty) {
        sb.append(s"${margin}l: ")
        left.toString(sb, margin + "    ")
      }
      if (mid != TST.Empty) {
        sb.append(s"${margin}m: ")
        mid.toString(sb, margin + "    ")
      }
      if (right != TST.Empty) {
        sb.append(s"${margin}r: ")
        right.toString(sb, margin + "    ")
      }
      //sb.append(margin)
      //sb.append(")\n")
    }
    else {
      //sb.append(")\n")
      sb.append("\n")
    }
  }

  override def toString: String = {
    val sb = new StringBuilder
    toString(sb, "")
    sb.toString
  }
}
