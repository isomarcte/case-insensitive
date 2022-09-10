package org.typelevel.ci

import cats.implicits._
import cats.kernel.laws.discipline._
import munit._
import org.scalacheck.Prop._
import org.typelevel.ci.testing.arbitraries._
import scala.math.signum
import scala.annotation.tailrec

final class CanonicalFullCIStringSuite extends ScalaCheckSuite {
  test("Distinct sequences of code points which represent the same canonical string should compare casslessly equal (Å)") {
    val a: String = new String("\u212b")
    val b: String = new String("\u0041\u030a")
    val c: String = new String("\u00c5")

    assertNotEquals(a, b)
    assertNotEquals(b, c)
    assertNotEquals(a, c)
    assertEquals(CanonicalFullCIString(a), CanonicalFullCIString(b))
    assertEquals(CanonicalFullCIString(b), CanonicalFullCIString(c))
    assertEquals(CanonicalFullCIString(a), CanonicalFullCIString(c))
  }
}
