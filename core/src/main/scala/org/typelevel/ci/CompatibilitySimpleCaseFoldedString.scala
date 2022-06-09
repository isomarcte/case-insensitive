/*
 * Copyright 2020 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.typelevel.ci

import cats._
import cats.kernel.LowerBounded
import java.text.Normalizer
import scala.annotation.tailrec

final case class CompatibilitySimpleCaseFoldedString private (override val toString: String)
    extends AnyVal

object CompatibilitySimpleCaseFoldedString {
  def apply(value: String): CompatibilitySimpleCaseFoldedString = {
    val nfdNormal: String =
      if (Normalizer.isNormalized(value, Normalizer.Form.NFD)) {
        value
      } else {
        Normalizer.normalize(value, Normalizer.Form.NFD)
      }

    val caseFold0: String =
      CaseFolding.simpleCaseFoldString(nfdNormal)

    val nfkdNormal0: String =
      Normalizer.normalize(caseFold0, Normalizer.Form.NFKD)

    val caseFold1: String =
      CaseFolding.simpleCaseFoldString(nfkdNormal0)

    val nfkdNormal1: String =
      Normalizer.normalize(caseFold1, Normalizer.Form.NFKD)

    new CompatibilitySimpleCaseFoldedString(nfkdNormal1)
  }

  val empty: CompatibilitySimpleCaseFoldedString =
    apply("")

  implicit val hashAndOrderForCompatibilitySimpleCaseFoldedString
      : Hash[CompatibilitySimpleCaseFoldedString] with Order[CompatibilitySimpleCaseFoldedString] =
    new Hash[CompatibilitySimpleCaseFoldedString] with Order[CompatibilitySimpleCaseFoldedString] {
      override def hash(x: CompatibilitySimpleCaseFoldedString): Int =
        x.hashCode

      override def compare(
          x: CompatibilitySimpleCaseFoldedString,
          y: CompatibilitySimpleCaseFoldedString): Int =
        x.toString.compare(y.toString)
    }

  implicit val orderingForCompatibilitySimpleCaseFoldedString
      : Ordering[CompatibilitySimpleCaseFoldedString] =
    hashAndOrderForCompatibilitySimpleCaseFoldedString.toOrdering

  implicit val showForCompatibilitySimpleCaseFoldedString: Show[CompatibilitySimpleCaseFoldedString] =
    Show.fromToString

  implicit val lowerBoundForCompatibilitySimpleCaseFoldedString
      : LowerBounded[CompatibilitySimpleCaseFoldedString] =
    new LowerBounded[CompatibilitySimpleCaseFoldedString] {
      override val partialOrder: PartialOrder[CompatibilitySimpleCaseFoldedString] =
        hashAndOrderForCompatibilitySimpleCaseFoldedString

      override val minBound: CompatibilitySimpleCaseFoldedString =
        empty
    }

  implicit val monoidForCompatibilitySimpleCaseFoldedString: Monoid[CompatibilitySimpleCaseFoldedString] =
    new Monoid[CompatibilitySimpleCaseFoldedString] {
      override val empty: CompatibilitySimpleCaseFoldedString = CompatibilitySimpleCaseFoldedString.empty

      override def combine(
          x: CompatibilitySimpleCaseFoldedString,
          y: CompatibilitySimpleCaseFoldedString): CompatibilitySimpleCaseFoldedString =
        CompatibilitySimpleCaseFoldedString(x.toString + y.toString)

      override def combineAll(
          xs: IterableOnce[CompatibilitySimpleCaseFoldedString]): CompatibilitySimpleCaseFoldedString = {
        val sb: StringBuilder = new StringBuilder
        xs.iterator.foreach(cfs => sb.append(cfs.toString))
        CompatibilitySimpleCaseFoldedString(sb.toString)
      }
    }
}
