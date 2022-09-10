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

final case class TurkicSimpleCaseFoldedString private (override val toString: String) extends AnyVal

object TurkicSimpleCaseFoldedString {
  def apply(value: String): TurkicSimpleCaseFoldedString =
    new TurkicSimpleCaseFoldedString(CaseFolding.turkicSimpleCaseFoldString(value))

  val empty: TurkicSimpleCaseFoldedString =
    apply("")

  implicit val hashAndOrderForTurkicSimpleCaseFoldedString
      : Hash[TurkicSimpleCaseFoldedString] with Order[TurkicSimpleCaseFoldedString] =
    new Hash[TurkicSimpleCaseFoldedString] with Order[TurkicSimpleCaseFoldedString] {
      override def hash(x: TurkicSimpleCaseFoldedString): Int =
        x.hashCode

      override def compare(
          x: TurkicSimpleCaseFoldedString,
          y: TurkicSimpleCaseFoldedString): Int =
        x.toString.compare(y.toString)
    }

  implicit val orderingForTurkicSimpleCaseFoldedString: Ordering[TurkicSimpleCaseFoldedString] =
    hashAndOrderForTurkicSimpleCaseFoldedString.toOrdering

  implicit val showForTurkicSimpleCaseFoldedString: Show[TurkicSimpleCaseFoldedString] =
    Show.fromToString

  implicit val lowerBoundForTurkicSimpleCaseFoldedString
      : LowerBounded[TurkicSimpleCaseFoldedString] =
    new LowerBounded[TurkicSimpleCaseFoldedString] {
      override val partialOrder: PartialOrder[TurkicSimpleCaseFoldedString] =
        hashAndOrderForTurkicSimpleCaseFoldedString

      override val minBound: TurkicSimpleCaseFoldedString =
        empty
    }

  implicit val monoidForTurkicSimpleCaseFoldedString: Monoid[TurkicSimpleCaseFoldedString] =
    new Monoid[TurkicSimpleCaseFoldedString] {
      override val empty: TurkicSimpleCaseFoldedString = TurkicSimpleCaseFoldedString.empty

      override def combine(
          x: TurkicSimpleCaseFoldedString,
          y: TurkicSimpleCaseFoldedString): TurkicSimpleCaseFoldedString =
        TurkicSimpleCaseFoldedString(x.toString + y.toString)

      override def combineAll(
          xs: IterableOnce[TurkicSimpleCaseFoldedString]): TurkicSimpleCaseFoldedString = {
        val sb: StringBuilder = new StringBuilder
        xs.iterator.foreach(cfs => sb.append(cfs.toString))
        TurkicSimpleCaseFoldedString(sb.toString)
      }
    }
}
