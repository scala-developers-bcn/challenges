import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.libs.json._
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class FlyAppSpec extends Specification {

  val flight0 = Json.obj(
    "id" -> "IB000",
    "from" -> "BCN",
    "to" -> "LONDON",
    "departure" -> "2014-01-08T12:35:00Z",
    "arrival" -> "2014-01-08T13:30:00Z",
    "status" -> "DELAYED"
  )

  val flight1 = Json.obj(
    "id" -> "IB001",
    "from" -> "BCN",
    "to" -> "PARIS",
    "departure" -> "2014-01-28T12:35:00Z",
    "arrival" -> "2014-01-28T13:30:00Z",
    "status" -> "ONTIME"
  )

  val flight2 = Json.obj(
    "id" -> "IB002",
    "from" -> "BCN",
    "to" -> "MAD",
    "departure" -> "2014-01-30T08:35:00Z",
    "arrival" -> "2014-01-30T09:15:00Z",
    "status" -> "ONTIME"
  )

  "Flights Application" should {

    "fail on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/boum")) must beNone
    }

    // GET /flights

    "get an empty list when no flights have been created" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights")).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result) must beEqualTo(Json.arr())
    }

    "ignore unexpected query parameters" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights")).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result) must beEqualTo(Json.arr())
    }

    // POST /flights

    "fail to create a flight without fields" in new WithApplication {
      val result = route(FakeRequest(POST, "/flights")).get

      status(result) must equalTo(BAD_REQUEST)
    }

    "fail to create a flight with incomplete input fields" in new WithApplication() {
      for {
        key <- List("id", "from", "to", "departure", "arrival", "status")
      } yield {
        val incompleteFlight = flight1.transform((JsPath \ key).json.prune).get
        val result = route(FakeRequest(POST, "/flights").withJsonBody(incompleteFlight)).get
        status(result) must equalTo(BAD_REQUEST)
      }
    }

    "fail to create a flight with wrong input fields" in new WithApplication() {
      for {
        e <- List(("id", Json.arr(1, 2, 3)), ("from", JsNumber(4)), ("to", Json.obj("a" -> "b")),
                    ("departure", JsString("hello")), ("arrival", Json.arr(1, 2)), ("status", JsNumber(7)))
      } yield {
        val wrongFlight = flight1 ++ Json.obj(e._1 -> e._2)
        val result = route(FakeRequest(POST, "/flights").withJsonBody(wrongFlight)).get
        status(result) must equalTo(BAD_REQUEST)
      }
    }

    "create new flights" in new WithApplication {
      List(flight0, flight1, flight2).foreach { flight =>
        val result = route(FakeRequest(POST, "/flights").withJsonBody(flight)).get

        status(result) must equalTo(CREATED)
        contentType(result) must beSome.which(_ == "application/json")
        charset(result) must beSome("utf-8")
        contentAsJson(result) must beEqualTo(Json.obj("id" -> flight \ "id"))
      }
    }

    // GET /flights?

    "get an empty list when no flights exist within time" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights?to=LONDON")).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result) must beEqualTo(Json.arr())
    }

    "get an empty list when no flights exist within time from the given location" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights?from=LONDON")).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result) must beEqualTo(Json.arr())
    }

    "get an empty list when no flights exist within time to the given location" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights?to=BCN")).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result) must beEqualTo(Json.arr())
    }

    "get the list of all available flights within time" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights")).get

      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result).asOpt[List[JsValue]].getOrElse(List()).length must beEqualTo(2)
    }
  }
}
