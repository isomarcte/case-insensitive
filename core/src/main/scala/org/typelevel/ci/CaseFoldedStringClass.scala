package org.typelevel.ci

trait CaseFoldedStringClass[A] extends Serializable {
  def fromString(value: String): A
  def asString(a: A): String

  // final

  final def transform[B](a: A)(f: String => B): B =
    f(asString(a))

  final def caseFoldString(value: String): String =
    asString(fromString(value))
}

object CaseFoldedStringClass {

  def apply[A](implicit ev: CaseFoldedStringClass[A]): CaseFoldedStringClass[A] = ev

  implicit val canonicalFullCaseFoldedStringInstance: CaseFoldedStringClass[CanonicalFullCaseFoldedString] =
    new CaseFoldedStringClass[CanonicalFullCaseFoldedString] {
      override def fromString(value: String): CanonicalFullCaseFoldedString =
        CanonicalFullCaseFoldedString(value)

      override def asString(a: CanonicalFullCaseFoldedString): String =
        a.toString
    }

  implicit val canonicalSimpleCaseFoldedStringInstance: CaseFoldedStringClass[CanonicalSimpleCaseFoldedString] =
    new CaseFoldedStringClass[CanonicalSimpleCaseFoldedString] {
      override def fromString(value: String): CanonicalSimpleCaseFoldedString =
        CanonicalSimpleCaseFoldedString(value)

      override def asString(a: CanonicalSimpleCaseFoldedString): String =
        a.toString
    }

  implicit val canonicalTurkicFullCaseFoldedStringInstance: CaseFoldedStringClass[CanonicalTurkicFullCaseFoldedString] =
    new CaseFoldedStringClass[CanonicalTurkicFullCaseFoldedString] {
      override def fromString(value: String): CanonicalTurkicFullCaseFoldedString =
        CanonicalTurkicFullCaseFoldedString(value)

      override def asString(a: CanonicalTurkicFullCaseFoldedString): String =
        a.toString
    }

  implicit val canonicalTurkicSimpleCaseFoldedStringInstance: CaseFoldedStringClass[CanonicalTurkicSimpleCaseFoldedString] =
    new CaseFoldedStringClass[CanonicalTurkicSimpleCaseFoldedString] {
      override def fromString(value: String): CanonicalTurkicSimpleCaseFoldedString =
        CanonicalTurkicSimpleCaseFoldedString(value)

      override def asString(a: CanonicalTurkicSimpleCaseFoldedString): String =
        a.toString
    }

  implicit val compatibilityFullCaseFoldedStringInstance: CaseFoldedStringClass[CompatibilityFullCaseFoldedString] =
    new CaseFoldedStringClass[CompatibilityFullCaseFoldedString] {
      override def fromString(value: String): CompatibilityFullCaseFoldedString =
        CompatibilityFullCaseFoldedString(value)

      override def asString(a: CompatibilityFullCaseFoldedString): String =
        a.toString
    }

  implicit val compatibilitySimpleCaseFoldedStringInstance: CaseFoldedStringClass[CompatibilitySimpleCaseFoldedString] =
    new CaseFoldedStringClass[CompatibilitySimpleCaseFoldedString] {
      override def fromString(value: String): CompatibilitySimpleCaseFoldedString =
        CompatibilitySimpleCaseFoldedString(value)

      override def asString(a: CompatibilitySimpleCaseFoldedString): String =
        a.toString
    }

  implicit val compatibilityTurkicFullCaseFoldedStringInstance: CaseFoldedStringClass[CompatibilityTurkicFullCaseFoldedString] =
    new CaseFoldedStringClass[CompatibilityTurkicFullCaseFoldedString] {
      override def fromString(value: String): CompatibilityTurkicFullCaseFoldedString =
        CompatibilityTurkicFullCaseFoldedString(value)

      override def asString(a: CompatibilityTurkicFullCaseFoldedString): String =
        a.toString
    }

  implicit val compatibilityTurkicSimpleCaseFoldedStringInstance: CaseFoldedStringClass[CompatibilityTurkicSimpleCaseFoldedString] =
    new CaseFoldedStringClass[CompatibilityTurkicSimpleCaseFoldedString] {
      override def fromString(value: String): CompatibilityTurkicSimpleCaseFoldedString =
        CompatibilityTurkicSimpleCaseFoldedString(value)

      override def asString(a: CompatibilityTurkicSimpleCaseFoldedString): String =
        a.toString
    }

  implicit val fullCaseFoldedStringInstance: CaseFoldedStringClass[FullCaseFoldedString] =
    new CaseFoldedStringClass[FullCaseFoldedString] {
      override def fromString(value: String): FullCaseFoldedString =
        FullCaseFoldedString(value)

      override def asString(a: FullCaseFoldedString): String =
        a.toString
    }

  implicit val simpleCaseFoldedStringInstance: CaseFoldedStringClass[SimpleCaseFoldedString] =
    new CaseFoldedStringClass[SimpleCaseFoldedString] {
      override def fromString(value: String): SimpleCaseFoldedString =
        SimpleCaseFoldedString(value)

      override def asString(a: SimpleCaseFoldedString): String =
        a.toString
    }

  implicit val turkicFullCaseFoldedStringInstance: CaseFoldedStringClass[TurkicFullCaseFoldedString] =
    new CaseFoldedStringClass[TurkicFullCaseFoldedString] {
      override def fromString(value: String): TurkicFullCaseFoldedString =
        TurkicFullCaseFoldedString(value)

      override def asString(a: TurkicFullCaseFoldedString): String =
        a.toString
    }

  implicit val turkicSimpleCaseFoldedStringInstance: CaseFoldedStringClass[TurkicSimpleCaseFoldedString] =
    new CaseFoldedStringClass[TurkicSimpleCaseFoldedString] {
      override def fromString(value: String): TurkicSimpleCaseFoldedString =
        TurkicSimpleCaseFoldedString(value)

      override def asString(a: TurkicSimpleCaseFoldedString): String =
        a.toString
    }
}
