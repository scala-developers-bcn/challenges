package chriszen.singlehash

import javax.xml.bind.DatatypeConverter

object Hash {
  def apply(b: Array[Byte]) = {
    new Hash(b)
  }

  def fromBase64(s: String) = {
    new Hash(DatatypeConverter.parseBase64Binary(s))
  }
}

class Hash(val bytes: Array[Byte]) {

  override def equals(other: Any) = {
    if (!other.isInstanceOf[Hash])
      false
    else {
      val o = other.asInstanceOf[Hash]
      if (bytes.length != o.bytes.length)
        false
      else {
        var i = 0
        while (i < bytes.length && bytes(i) == o.bytes(i))
          i += 1
        i == bytes.length
      }
    }
  }

  override def toString = DatatypeConverter.printBase64Binary(bytes)
}