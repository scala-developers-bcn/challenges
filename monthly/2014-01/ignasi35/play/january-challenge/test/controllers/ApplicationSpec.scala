package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {

  "Application" should {

    "return 200 on flights/" in {
      running(FakeApplication()) {
        val home = route(FakeRequest(GET, "/flights")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "application/json")
      }
    }
  }
}