package org.typelevel.ci

import cats.implicits._
import cats.kernel.laws.discipline._
import munit._
import org.scalacheck.Prop._
import org.typelevel.ci.testing.arbitraries._
import scala.math.signum
import scala.annotation.tailrec

final class CanonicalSimpleCIStringSuite extends ScalaCheckSuite {
  test("Distinct sequences of code points which represent the same canonical string should compare casslessly equal as long as the byte length of the String is unchanged (Å)") {
    val a: String = new String("\u212b")
    val b: String = new String("\u0041\u030a")
    val c: String = new String("\u00c5")

    assertNotEquals(a, b)
    assertNotEquals(b, c)
    assertNotEquals(a, c)
    assertEquals(CanonicalSimpleCIString(a), CanonicalSimpleCIString(c))

    // Equal under Full case folding, but not simple
    assertNotEquals(CanonicalSimpleCIString(b), CanonicalSimpleCIString(a))
    assertNotEquals(CanonicalSimpleCIString(b), CanonicalSimpleCIString(c))
  }
}
