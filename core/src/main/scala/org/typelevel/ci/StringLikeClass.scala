package org.typelevel.ci

import scala.annotation.tailrec
import java.nio.charset.Charset

trait StringLikeClass[A] extends Serializable {
  def asString(a: A): String

  def isEmpty(a: A): Boolean

  def charAt(a: A)(index: Int): Int

  def chars(a: A): Vector[Char]

  def contains(a: A)(value: A): Boolean

  def endsWith(a: A)(suffix: A): Boolean

  def formatted(a: A)(args: Any*): A

  def codePoints(a: A): Vector[Int]

  def indent(a: A)(n: Int): A

  def indexOf(a: A)(char: Char, fromIndex: Int): Int

  def indexOf(a: A)(codePoint: Int, fromIndex: Int): Int

  def isBlank(a: A): Boolean

  // default

  def codePointCount(a: A): Int =
    codePoints(a).size

  def codePointAt(a: A)(index: Int): Int =
    codePoints(a)(index)

  def codePointBefore(a: A)(index: Int): Int =
    codePoints(a)(index - 1)

  def getBytes(a: A): Array[Byte] =
    asString(a).getBytes

  def getBytes(a: A)(charset: String): Array[Byte] =
    asString(a).getBytes(charset)

  def getBytes(a: A)(charset: Charset): Array[Byte] =
    asString(a).getBytes(charset)

  def indexOf(char: Char): Int =
    indexOf(char, 0)

  def indexOf(codePoint: Int): Int =
    indexOf(codePoint, 0)

  def indexOfOpt(char: Char): Option[Int] =
    indexOf(char) match {
      case n if n >= 0 => Some(n)
      case _ => None
    }

  def indexOfOpt(codePoint: Int): Option[Int] =
    indexOf(codePoint) match {
      case n if n >= 0 => Some(n)
      case _ => None
    }

  def indexOfOpt(char: Char, fromIndex: Int): Option[Int] =
    indexOf(char, fromIndex) match {
      case n if n >= 0 => Some(n)
      case _ => None
    }

  def indexOfOpt(codePoint: Int, fromIndex: Int): Option[Int] =
    indexOf(codePoint, fromIndex) match {
      case n if n >= 0 => Some(n)
      case _ => None
    }
}
