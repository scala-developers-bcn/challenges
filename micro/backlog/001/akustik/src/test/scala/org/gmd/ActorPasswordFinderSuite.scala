package org.gmd

class ActorPasswordFinderSuite extends PasswordFinderSuite {
  val subjectUnderTest = new ActorPasswordFinder
  override val suiteName = "Actor"
}
