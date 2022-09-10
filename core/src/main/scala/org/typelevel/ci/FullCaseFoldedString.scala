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

final case class FullCaseFoldedString private (override val toString: String) extends AnyVal {
  private final def copy(toString: String = toString): FullCaseFoldedString =
    FullCaseFoldedString(toString)
}

object FullCaseFoldedString {
  def apply(value: String): FullCaseFoldedString =
    new FullCaseFoldedString(CaseFolding.fullCaseFoldString(value))

  val empty: FullCaseFoldedString =
    apply("")

  implicit val hashAndOrderForFullCaseFoldedString
      : Hash[FullCaseFoldedString] with Order[FullCaseFoldedString] =
    new Hash[FullCaseFoldedString] with Order[FullCaseFoldedString] {
      override def hash(x: FullCaseFoldedString): Int =
        x.hashCode

      override def compare(
          x: FullCaseFoldedString,
          y: FullCaseFoldedString): Int =
        x.toString.compare(y.toString)
    }

  implicit val orderingForFullCaseFoldedString: Ordering[FullCaseFoldedString] =
    hashAndOrderForFullCaseFoldedString.toOrdering

  implicit val showForFullCaseFoldedString: Show[FullCaseFoldedString] =
    Show.fromToString

  implicit val lowerBoundForFullCaseFoldedString
      : LowerBounded[FullCaseFoldedString] =
    new LowerBounded[FullCaseFoldedString] {
      override val partialOrder: PartialOrder[FullCaseFoldedString] =
        hashAndOrderForFullCaseFoldedString

      override val minBound: FullCaseFoldedString =
        empty
    }

  implicit val monoidForFullCaseFoldedString: Monoid[FullCaseFoldedString] =
    new Monoid[FullCaseFoldedString] {
      override val empty: FullCaseFoldedString = FullCaseFoldedString.empty

      override def combine(
          x: FullCaseFoldedString,
          y: FullCaseFoldedString): FullCaseFoldedString =
        FullCaseFoldedString(x.toString + y.toString)

      override def combineAll(
          xs: IterableOnce[FullCaseFoldedString]): FullCaseFoldedString = {
        val sb: StringBuilder = new StringBuilder
        xs.iterator.foreach(cfs => sb.append(cfs.toString))
        FullCaseFoldedString(sb.toString)
      }
    }
}
