package org.typelevel.ci

import cats._
import cats.syntax.all._
import cats.kernel.LowerBounded
import cats.instances.partialOrder

sealed abstract class CaselessString[A] extends Product with Serializable with Ordered[CaselessString[A]] {
  def asCaseFoldedString: A
  def isEmpty: Boolean
  def transform(f: String => String): CaselessString[A]
  def caselessCharLength: Int

  // final

  final def trim: CaselessString[A] =
    transform(_.trim)

  final def nonEmpty: Boolean = isEmpty == false

  // methods for change the caseless variant

  final def changeCaselessType[B: CaseFoldedStringClass: Order]: CaselessString[B] =
    CaselessString[B](toString)

  final def asCanonicalFullCaseless: CIString =
    changeCaselessType

  final def asCanonicalSimpleCaseless: SimpleCIString =
    changeCaselessType

  final def asTurkicCanonicalFullCaseless: TurkicCIString =
    changeCaselessType

  final def asTurkicCanonicalSimpleCaseless: TurkicSimpleCIString =
    changeCaselessType

  final def asCompatibilityFullCaseless: CompatibilityCIString =
    changeCaselessType

  final def asCompatibilitySimpleCaseless: CompatibilitySimpleCIString =
    changeCaselessType

  final def asTurkicCompatibilityFullCaseless: CompatibilityTurkicCIString =
    changeCaselessType

  final def asTurkicCompatibilitySimpleCaseless: CompatibilityTurkicSimpleCIString =
    changeCaselessType

  override final def hashCode(): Int =
    asCaseFoldedString.hashCode()
}

object CaselessString {

  private[this] final case class CaselessStringImpl[A: CaseFoldedStringClass: Order](override val toString: String) extends CaselessString[A] {
    override lazy val asCaseFoldedString: A =
      CaseFoldedStringClass[A].fromString(toString)

    override def isEmpty: Boolean =
      CaseFoldedStringClass[A].asString(asCaseFoldedString).isEmpty

    override def transform(f: String => String): CaselessString[A] =
      CaselessStringImpl[A](f(toString))

    override def caselessCharLength: Int =
      CaseFoldedStringClass[A].asString(asCaseFoldedString).length

    override def compare(that: CaselessString[A]): Int =
      Order[CaselessString[A]].compare(this, that)
  }

  def apply[A: CaseFoldedStringClass: Order](value: String): CaselessString[A] =
    CaselessStringImpl(value)

  def empty[A: CaseFoldedStringClass: Order]: CaselessString[A] =
    CaselessString[A]("")

  implicit def hashAndOrderForCaselessString[A: Order]: Hash[CaselessString[A]] with Order[CaselessString[A]] =
    new Hash[CaselessString[A]] with Order[CaselessString[A]] {
      override def hash(x: CaselessString[A]): Int =
        x.hashCode

      override def compare(x: CaselessString[A], y: CaselessString[A]): Int =
        x.asCaseFoldedString.compare(y.asCaseFoldedString)
    }

  implicit def showForCaselessString[A]: Show[CaselessString[A]] =
    Show.fromToString

  implicit def monoidForCaselessString[A: CaseFoldedStringClass: Order]: Monoid[CaselessString[A]] =
    new Monoid[CaselessString[A]] {
      override val empty: CaselessString[A] = CaselessString.empty

      override def combine(x: CaselessString[A], y: CaselessString[A]): CaselessString[A] =
        CaselessString(x.toString + y.toString)

      override def combineAll(xs: IterableOnce[CaselessString[A]]): CaselessString[A] = {
        val sb: StringBuilder = new StringBuilder

        xs.iterator.foreach(value => sb.append(value.toString))

        CaselessString[A](sb.toString)
      }
    }

  implicit def lowerBoundInstanceForCaselessString[A: CaseFoldedStringClass: Order]: LowerBounded[CaselessString[A]] =
    new LowerBounded[CaselessString[A]] {
      override def partialOrder: PartialOrder[CaselessString[A]] =
        Order[CaselessString[A]]

      override val minBound: CaselessString[A] =
        CaselessString.empty
    }
}
