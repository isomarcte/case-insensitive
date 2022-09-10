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
import cats.kernel._
import scala.annotation.tailrec

final case class TurkicFullCaseFoldedString private (override val toString: String) extends AnyVal

object TurkicFullCaseFoldedString {
  def apply(value: String): TurkicFullCaseFoldedString =
    new TurkicFullCaseFoldedString(CaseFolding.turkicFullCaseFoldString(value))

  val empty: TurkicFullCaseFoldedString =
    apply("")

  implicit val hashAndOrderForTurkicFullCaseFoldedString
      : Hash[TurkicFullCaseFoldedString] with Order[TurkicFullCaseFoldedString] =
    new Hash[TurkicFullCaseFoldedString] with Order[TurkicFullCaseFoldedString] {
      override def hash(x: TurkicFullCaseFoldedString): Int =
        x.hashCode

      override def compare(
          x: TurkicFullCaseFoldedString,
          y: TurkicFullCaseFoldedString): Int =
        x.toString.compare(y.toString)
    }

  implicit val orderingForTurkicFullCaseFoldedString: Ordering[TurkicFullCaseFoldedString] =
    hashAndOrderForTurkicFullCaseFoldedString.toOrdering

  implicit val showForTurkicFullCaseFoldedString: Show[TurkicFullCaseFoldedString] =
    Show.fromToString

  implicit val lowerBoundForTurkicFullCaseFoldedString
      : LowerBounded[TurkicFullCaseFoldedString] =
    new LowerBounded[TurkicFullCaseFoldedString] {
      override val partialOrder: PartialOrder[TurkicFullCaseFoldedString] =
        hashAndOrderForTurkicFullCaseFoldedString

      override val minBound: TurkicFullCaseFoldedString =
        empty
    }

  implicit val monoidForTurkicFullCaseFoldedString: Monoid[TurkicFullCaseFoldedString] =
    new Monoid[TurkicFullCaseFoldedString] {
      override val empty: TurkicFullCaseFoldedString = TurkicFullCaseFoldedString.empty

      override def combine(
          x: TurkicFullCaseFoldedString,
          y: TurkicFullCaseFoldedString): TurkicFullCaseFoldedString =
        TurkicFullCaseFoldedString(x.toString + y.toString)

      override def combineAll(
          xs: IterableOnce[TurkicFullCaseFoldedString]): TurkicFullCaseFoldedString = {
        val sb: StringBuilder = new StringBuilder
        xs.iterator.foreach(cfs => sb.append(cfs.toString))
        TurkicFullCaseFoldedString(sb.toString)
      }
    }
}
