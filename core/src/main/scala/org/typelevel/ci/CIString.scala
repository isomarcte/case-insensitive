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

object CIString {

  def apply(value: String): CIString =
    CaselessString(value)

  val empty: CIString = CaselessString.empty

  def unapply(value: CIString): Some[String] =
    Some(value.toString)
}

object SimpleCIString {

  def apply(value: String): SimpleCIString =
    CaselessString(value)

  val empty: SimpleCIString = CaselessString.empty

  def unapply(value: SimpleCIString): Some[String] =
    Some(value.toString)
}

object TurkicCIString {

  def apply(value: String): TurkicCIString =
    CaselessString(value)

  val empty: TurkicCIString = CaselessString.empty

  def unapply(value: TurkicCIString): Some[String] =
    Some(value.toString)
}

object TurkicSimpleCIString {

  def apply(value: String): TurkicSimpleCIString =
    CaselessString(value)

  val empty: TurkicSimpleCIString = CaselessString.empty

  def unapply(value: TurkicSimpleCIString): Some[String] =
    Some(value.toString)
}

object CompatibilityCIString {

  def apply(value: String): CompatibilityCIString =
    CaselessString(value)

  val empty: CompatibilityCIString = CaselessString.empty

  def unapply(value: CompatibilityCIString): Some[String] =
    Some(value.toString)
}

object CompatibilitySimpleCIString {

  def apply(value: String): CompatibilitySimpleCIString =
    CaselessString(value)

  val empty: CompatibilitySimpleCIString = CaselessString.empty

  def unapply(value: CompatibilitySimpleCIString): Some[String] =
    Some(value.toString)
}

object CompatibilityTurkicCIString {

  def apply(value: String): CompatibilityTurkicCIString =
    CaselessString(value)

  val empty: CompatibilityTurkicCIString = CaselessString.empty

  def unapply(value: CompatibilityTurkicCIString): Some[String] =
    Some(value.toString)
}

object CompatibilityTurkicSimpleCIString {

  def apply(value: String): CompatibilityTurkicSimpleCIString =
    CaselessString(value)

  val empty: CompatibilityTurkicSimpleCIString = CaselessString.empty

  def unapply(value: CompatibilityTurkicSimpleCIString): Some[String] =
    Some(value.toString)
}
