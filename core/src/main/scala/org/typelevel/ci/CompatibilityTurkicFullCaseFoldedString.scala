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

final case class CompatibilityTurkicFullCaseFoldedString private (override val toString: String)
    extends AnyVal

object CompatibilityTurkicFullCaseFoldedString {

  def apply(value: String): CompatibilityTurkicFullCaseFoldedString = {
    val nfdNormal: String =
      if (Normalizer.isNormalized(value, Normalizer.Form.NFD)) {
        value
      } else {
        Normalizer.normalize(value, Normalizer.Form.NFD)
      }

    val caseFold0: String =
      CaseFolding.turkicFullCaseFoldString(nfdNormal)

    val nfkdNormal0: String =
      Normalizer.normalize(caseFold0, Normalizer.Form.NFKD)

    val caseFold1: String =
      CaseFolding.turkicFullCaseFoldString(nfkdNormal0)

    val nfkdNormal1: String =
      Normalizer.normalize(caseFold1, Normalizer.Form.NFKD)

    new CompatibilityTurkicFullCaseFoldedString(nfkdNormal1)
  }

  val empty: CompatibilityTurkicFullCaseFoldedString =
    apply("")

  implicit val hashAndOrderForCompatibilityTurkicFullCaseFoldedString
      : Hash[CompatibilityTurkicFullCaseFoldedString] with Order[CompatibilityTurkicFullCaseFoldedString] =
    new Hash[CompatibilityTurkicFullCaseFoldedString] with Order[CompatibilityTurkicFullCaseFoldedString] {
      override def hash(x: CompatibilityTurkicFullCaseFoldedString): Int =
        x.hashCode

      override def compare(
          x: CompatibilityTurkicFullCaseFoldedString,
          y: CompatibilityTurkicFullCaseFoldedString): Int =
        x.toString.compare(y.toString)
    }

  implicit val orderingForCompatibilityTurkicFullCaseFoldedString
      : Ordering[CompatibilityTurkicFullCaseFoldedString] =
    hashAndOrderForCompatibilityTurkicFullCaseFoldedString.toOrdering

  implicit val showForCompatibilityTurkicFullCaseFoldedString
      : Show[CompatibilityTurkicFullCaseFoldedString] =
    Show.fromToString

  implicit val lowerBoundForCompatibilityTurkicFullCaseFoldedString
      : LowerBounded[CompatibilityTurkicFullCaseFoldedString] =
    new LowerBounded[CompatibilityTurkicFullCaseFoldedString] {
      override val partialOrder: PartialOrder[CompatibilityTurkicFullCaseFoldedString] =
        hashAndOrderForCompatibilityTurkicFullCaseFoldedString

      override val minBound: CompatibilityTurkicFullCaseFoldedString =
        empty
    }

  implicit val monoidForCompatibilityTurkicFullCaseFoldedString
      : Monoid[CompatibilityTurkicFullCaseFoldedString] =
    new Monoid[CompatibilityTurkicFullCaseFoldedString] {
      override val empty: CompatibilityTurkicFullCaseFoldedString =
        CompatibilityTurkicFullCaseFoldedString.empty

      override def combine(
          x: CompatibilityTurkicFullCaseFoldedString,
          y: CompatibilityTurkicFullCaseFoldedString): CompatibilityTurkicFullCaseFoldedString =
        CompatibilityTurkicFullCaseFoldedString(x.toString + y.toString)

      override def combineAll(xs: IterableOnce[CompatibilityTurkicFullCaseFoldedString])
          : CompatibilityTurkicFullCaseFoldedString = {
        val sb: StringBuilder = new StringBuilder
        xs.iterator.foreach(cfs => sb.append(cfs.toString))
        CompatibilityTurkicFullCaseFoldedString(sb.toString)
      }
    }
}
