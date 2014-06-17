package org.gmd

class ParallelMapPasswordFinderSuite extends PasswordFinderSuite {
  val subjectUnderTest = new ParallelMapPasswordFinder
  override val suiteName = "Parallel"
}