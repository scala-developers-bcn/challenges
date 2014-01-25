package controllers

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends Specification {

  "Application" should {

    "return 200 on flights/" in {
      running(FakeApplication()) {
        val flights = route(FakeRequest(GET, "/flights")).get
        status(flights) must equalTo(OK)
        contentType(flights) must beSome.which(_ == "application/json")
        contentAsString(flights) must equalTo(
          """[{"status":"ON_TIME","origin":"BOS","destiny":"CHG","gate":"19B"}]""")
      }
    }

  }

}