package org.gmd

import scala.io.Codec
import java.io.InputStream
import java.io.Writer
import java.io.Closeable

trait InOutOps {
  implicit val codec = Codec.UTF8
  
  def using[A, R <: Closeable](r: R)(f: R => A): A =
    try {
      f(r)
    } finally {
      if (r != null)
        r.close()
    }
}