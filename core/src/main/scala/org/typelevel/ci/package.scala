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

package org.typelevel

/**
  * @define terms     * Several terms have special meaning here.
  * - Case folding
  *   - This is the process of transforming all letters into a form suitable
  *     for caseless comparison. This does not mean that all letters will be
  *     in lower or upper case. Most Unicode code points are transformed into
  *     their lower case representation, but some are transformed in to
  *     their upper case representation.
  * - Canonical/Compatibility Equivalence
  *   - These are two methods for determining if two sets of Unicode code points are equivalent in terms of what they represent.
  *   - Canonical
  *     - From UTS15: "Canonical equivalence is a fundamental equivalency
  *       between characters or sequences of characters which represent the
  *       same abstract character, and which when correctly displayed should
  *       always have the same visual appearance and behavior."
  *   - Compatibility
  *     - From UTS15: "Compatibility equivalence is a weaker type of
  *       equivalence between characters or sequences of characters which
  *       represent the same abstract character (or sequence of abstract
  *       characters), but which may have distinct visual appearances or
  *       behaviors."
  * - Normalization
  *   - From UTS15: "Unicode Normalization Forms are formally defined
  *     normalizations of Unicode strings which make it possible to
  *     determine whether any two Unicode strings are equivalent to each
  *     other. Depending on the particular Unicode Normalization Form, that
  *     equivalence can either be a canonical equivalence or a compatibility
  *     equivalence."
  * - Full/Simple
  *   - This terminology is used by the Unicode case folding documentation to
  *     differentiate between case folding which can change the ''glyph length
  *     '' of a string and case folding which can not. Full case folding is
  *     technically more correct, but may change the byte length of a string,
  *     simple case folding may not change the glyph length of a string, but
  *     there exists some Unicode strings which are caselessly equivalent but
  *     will which will fail to compare as such under simple case folding.
  *
  */
package object ci {

  type FullCIString = CaselessString[FullCaseFoldedString]

  type SimpleCIString = CaselessString[SimpleCaseFoldedString]

  type TurkicFullCIString = CaselessString[TurkicFullCaseFoldedString]

  type TurkicSimpleCIString = CaselessString[TurkicSimpleCaseFoldedString]

  /** A case insensitive representation of a `String`, based on the canonical
    * full cased folded representation of the given `String`.
    *
    * $terms
    *
    * There are several different ways to define a case insensitive match with Unicode. According to
    * the Unicode standard, this is the "most correct" definition. If you are just looking for a case
    * insensitive `String`, you should either use this or [[CanonicalFullCaseFoldedString]].
    *
    * The only difference is whether or not you want to keep track of the original input `String`
    * value. If you don't care about that, then [[CanonicalFullCaseFoldedString]] uses less memory and
    * is likely ''slightly'' faster for most operations.
    *
    * {{{
    * scala> CanonicalFullCIString("ß")
    * val res0: org.typelevel.ci.CanonicalFullCIString = ß
    *
    * scala> CanonicalFullCaseFoldedString("ß")
    * val res1: org.typelevel.ci.CanonicalFullCaseFoldedString = ss
    *
    * scala> res0.asCaseFoldedString == res1
    * val res2: Boolean = true
    *
    * scala> res0.toString
    * val res3: String = ß
    *
    * scala> res1.toString
    * val res4: String = ss
    *
    * scala> res0.asCanonicalFullCaseFoldedString.toString
    * val res5: String = ss
    * }}}
    *
    * @see
    *   [[https://www.unicode.org/versions/Unicode14.0.0/ch03.pdf#G34145 Unicode Caseless Matching]]
    * @see [[https://www.unicode.org/Public/UCD/latest/ucd/CaseFolding.txt Unicode Case Folding]]
    * @see [[https://www.unicode.org/reports/tr15/#Canon_Compat_Equivalence Canonical/Compatibility Equivalence]]
    */
  type CanonicalFullCIString = CaselessString[CanonicalFullCaseFoldedString]

  /** A case insensitive representation of a `String`, based on the canonical
    * simple cased folded representation of the given `String`.
    *
    * There are several different ways to define a case insensitive match with Unicode. According to
    * the Unicode standard, this is the "most correct" definition. If you are just looking for a case
    * insensitive `String`, you should either use this or [[CanonicalFullCaseFoldedString]].
    *
    * The only difference is whether or not you want to keep track of the original input `String`
    * value. If you don't care about that, then [[CanonicalFullCaseFoldedString]] uses less memory and
    * is likely ''slightly'' faster for most operations.
    *
    * {{{
    * scala> CanonicalFullCIString("ß")
    * val res0: org.typelevel.ci.CanonicalFullCIString = ß
    *
    * scala> CanonicalFullCaseFoldedString("ß")
    * val res1: org.typelevel.ci.CanonicalFullCaseFoldedString = ss
    *
    * scala> res0.asCaseFoldedString == res1
    * val res2: Boolean = true
    *
    * scala> res0.toString
    * val res3: String = ß
    *
    * scala> res1.toString
    * val res4: String = ss
    *
    * scala> res0.asCanonicalFullCaseFoldedString.toString
    * val res5: String = ss
    * }}}
    *
    * @see
    *   [[https://www.unicode.org/versions/Unicode14.0.0/ch03.pdf#G34145 Unicode Caseless Matching]]
    */
  type CanonicalSimpleCIString = CaselessString[CanonicalSimpleCaseFoldedString]

  type CanonicalTurkicFullCIString = CaselessString[CanonicalTurkicFullCaseFoldedString]

  type CanonicalTurkicSimpleCIString = CaselessString[CanonicalTurkicSimpleCaseFoldedString]

  type CompatibilityFullCIString = CaselessString[CompatibilityFullCaseFoldedString]

  type CompatibilitySimpleCIString = CaselessString[CompatibilitySimpleCaseFoldedString]

  type CompatibilityTurkicFullCIString = CaselessString[CompatibilityTurkicFullCaseFoldedString]

  type CompatibilityTurkicSimpleCIString = CaselessString[CompatibilityTurkicSimpleCaseFoldedString]

  /** @see [[CanonicalFullCIString]]
    */
  type CIString = CanonicalFullCIString

  // TODO: Restore this if/when I can get this working with
  //       CaselessString[CanonicalFullCaseFoldedString].

  // final implicit class CIStringSyntax(sc: StringContext) {

  //   /** Provides a `ci` interpolator, similar to the `s` interpolator. */
  //   def ci(args: Any*): CIString = CIString(sc.s(args: _*))

  //   object ci {

  //     /** A globbing CIString matcher, similar to the `s` matcher. */
  //     def unapplySeq(ci: CIString): Option[Seq[CIString]] = glob(sc.parts, ci)
  //   }
  // }

  // // Adapted from https://github.com/scala/scala/blob/v2.13.5/src/library/scala/StringContext.scala#L209
  // // Originally inspired by https://research.swtch.com/glob
  // private def glob(patternChunksRaw: Seq[String], input: CIString): Option[Seq[CIString]] = {
  //   var patternIndex = 0
  //   var inputIndex = 0
  //   var nextPatternIndex = 0
  //   var nextInputIndex = 0

  //   val patternChunks: Seq[CIString] = patternChunksRaw.map(CIString.apply)
  //   val numWildcards = patternChunks.length - 1
  //   val matchStarts = Array.fill(numWildcards)(-1)
  //   val matchEnds = Array.fill(numWildcards)(-1)

  //   val nameLength = input.caselessCharLength
  //   // The final pattern is as long as all the chunks, separated by 1-character
  //   // glob-wildcard placeholders
  //   val patternLength = {
  //     var n = numWildcards
  //     for (chunk <- patternChunks)
  //       n += chunk.caselessCharLength
  //     n
  //   }

  //   // Convert the input pattern chunks into a single sequence of shorts; each
  //   // non-negative short represents a character, while -1 represents a glob wildcard
  //   val pattern = {
  //     val arr = new Array[Short](patternLength)
  //     var i = 0
  //     var first = true
  //     for (chunk <- patternChunks) {
  //       if (first) first = false
  //       else {
  //         arr(i) = -1
  //         i += 1
  //       }
  //       for (c <- chunk.asCaseFoldedString.toString) {
  //         arr(i) = c.toShort
  //         i += 1
  //       }
  //     }
  //     arr
  //   }

  //   // Lookup table for each character in the pattern to check whether or not
  //   // it refers to a glob wildcard; a non-negative integer indicates which
  //   // glob wildcard it represents, while -1 means it doesn't represent any
  //   val matchIndices = {
  //     val arr = Array.fill(patternLength + 1)(-1)
  //     var i = 0
  //     var j = 0
  //     for (chunk <- patternChunks)
  //       if (j < numWildcards) {
  //         i += chunk.caselessCharLength
  //         arr(i) = j
  //         i += 1
  //         j += 1
  //       }
  //     arr
  //   }

  //   while (patternIndex < patternLength || inputIndex < nameLength) {
  //     matchIndices(patternIndex) match {
  //       case -1 => // do nothing
  //       case n =>
  //         matchStarts(n) = matchStarts(n) match {
  //           case -1 => inputIndex
  //           case s => math.min(s, inputIndex)
  //         }
  //         matchEnds(n) = matchEnds(n) match {
  //           case -1 => inputIndex
  //           case s => math.max(s, inputIndex)
  //         }
  //     }

  //     val continue = if (patternIndex < patternLength) {
  //       val c = pattern(patternIndex)
  //       c match {
  //         case -1 => // zero-or-more-character wildcard
  //           // Try to match at nx. If that doesn't work out, restart at nx+1 next.
  //           nextPatternIndex = patternIndex
  //           nextInputIndex = inputIndex + 1
  //           patternIndex += 1
  //           true
  //         case _ => // ordinary character
  //           if (inputIndex < nameLength && eqCi(input.toString(inputIndex), c.toChar)) {
  //             patternIndex += 1
  //             inputIndex += 1
  //             true
  //           } else {
  //             false
  //           }
  //       }
  //     } else false

  //     // Mismatch. Maybe restart.
  //     if (!continue) {
  //       if (0 < nextInputIndex && nextInputIndex <= nameLength) {
  //         patternIndex = nextPatternIndex
  //         inputIndex = nextInputIndex
  //       } else {
  //         return None
  //       }
  //     }
  //   }

  //   // Matched all of pattern to all of name. Success.
  //   Some(
  //     compat.unsafeWrapArray(
  //       Array.tabulate(patternChunks.length - 1)(n =>
  //         CIString(input.toString.slice(matchStarts(n), matchEnds(n))))
  //     ))
  // }
}
