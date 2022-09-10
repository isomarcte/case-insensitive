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

import cats.Show
import cats.kernel.{Hash, LowerBounded, Monoid, Order, PartialOrder}
import java.io.Serializable
import org.typelevel.ci.compat._
import scala.math.Ordered

object CanonicalFullCIString {

  def apply(value: String): CIString =
    CaselessString(value)

  def empty: CanonicalFullCIString = CaselessString.empty

  def unapply(value: CanonicalFullCIString): Some[String] =
    Some(value.toString)
}

// For backwards compatibility and ergonomics
object CIString {

  def apply(value: String): CIString =
    CanonicalFullCIString(value)

  def empty: CanonicalFullCIString = CanonicalFullCIString.empty

  def unapply(value: CanonicalFullCIString): Some[String] =
    CanonicalFullCIString.unapply(value)
}

object CanonicalSimpleCIString {

  def apply(value: String): CanonicalSimpleCIString =
    CaselessString(value)

  def empty: CanonicalSimpleCIString = CaselessString.empty

  def unapply(value: CanonicalSimpleCIString): Some[String] =
    Some(value.toString)
}

object CanonicalTurkicFullCIString {

  def apply(value: String): CanonicalTurkicFullCIString =
    CaselessString(value)

  def empty: CanonicalTurkicFullCIString = CaselessString.empty

  def unapply(value: CanonicalTurkicFullCIString): Some[String] =
    Some(value.toString)
}

object CanonicalTurkicSimpleCIString {

  def apply(value: String): CanonicalTurkicSimpleCIString =
    CaselessString(value)

  def empty: CanonicalTurkicSimpleCIString = CaselessString.empty

  def unapply(value: CanonicalTurkicSimpleCIString): Some[String] =
    Some(value.toString)
}

object FullCIString {

  def apply(value: String): CIString =
    CaselessString(value)

  def empty: CIString = CaselessString.empty

  def unapply(value: CIString): Some[String] =
    Some(value.toString)
}

object SimpleCIString {

  def apply(value: String): SimpleCIString =
    CaselessString(value)

  def empty: SimpleCIString = CaselessString.empty

  def unapply(value: SimpleCIString): Some[String] =
    Some(value.toString)
}

object TurkicFullCIString {

  def apply(value: String): TurkicFullCIString =
    CaselessString(value)

  def empty: TurkicFullCIString = CaselessString.empty

  def unapply(value: TurkicFullCIString): Some[String] =
    Some(value.toString)
}

object TurkicSimpleCIString {

  def apply(value: String): TurkicSimpleCIString =
    CaselessString(value)

  def empty: TurkicSimpleCIString = CaselessString.empty

  def unapply(value: TurkicSimpleCIString): Some[String] =
    Some(value.toString)
}

object CompatibilityFullCIString {

  def apply(value: String): CompatibilityFullCIString =
    CaselessString(value)

  def empty: CompatibilityFullCIString = CaselessString.empty

  def unapply(value: CompatibilityFullCIString): Some[String] =
    Some(value.toString)
}

object CompatibilitySimpleCIString {

  def apply(value: String): CompatibilitySimpleCIString =
    CaselessString(value)

  def empty: CompatibilitySimpleCIString = CaselessString.empty

  def unapply(value: CompatibilitySimpleCIString): Some[String] =
    Some(value.toString)
}

object CompatibilityTurkicFullCIString {

  def apply(value: String): CompatibilityTurkicFullCIString =
    CaselessString(value)

  def empty: CompatibilityTurkicFullCIString = CaselessString.empty

  def unapply(value: CompatibilityTurkicFullCIString): Some[String] =
    Some(value.toString)
}

object CompatibilityTurkicSimpleCIString {

  def apply(value: String): CompatibilityTurkicSimpleCIString =
    CaselessString(value)

  def empty: CompatibilityTurkicSimpleCIString = CaselessString.empty

  def unapply(value: CompatibilityTurkicSimpleCIString): Some[String] =
    Some(value.toString)
}
