import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.libs.json._
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString
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
    "departure" -> "2114-01-28T12:35:00Z",
    "arrival" -> "2114-01-28T13:30:00Z",
    "status" -> "ONTIME"
  )

  val flight2 = Json.obj(
    "id" -> "IB002",
    "from" -> "BCN",
    "to" -> "MAD",
    "departure" -> "2114-01-30T08:35:00Z",
    "arrival" -> "2114-01-30T09:15:00Z",
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
      List("id", "from", "to", "departure", "arrival", "status").map { key =>
        val incompleteFlight = flight1.transform((JsPath \ key).json.prune).get
        val result = route(FakeRequest(POST, "/flights").withJsonBody(incompleteFlight)).get
        status(result) must equalTo(BAD_REQUEST)
      }
    }

    "fail to create a flight with wrong input fields" in new WithApplication() {
      List(
        ("id", Json.arr(1, 2, 3)),
        ("from", JsNumber(4)),
        ("to", Json.obj("a" -> "b")),
        ("departure", JsString("hello")),
        ("arrival", Json.arr(1, 2)),
        ("status", JsNumber(7))).map { e=>

        val wrongFlight = flight1 ++ Json.obj(e._1 -> e._2)
        val result = route(FakeRequest(POST, "/flights").withJsonBody(wrongFlight)).get
        status(result) must equalTo(BAD_REQUEST)
      }
    }

    // GET /flights?

    "get an empty list when no flights exist within time" in new WithApplication {
      val result = route(FakeRequest(GET, "/flights")).get
      status(result) must equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      charset(result) must beSome("utf-8")
      contentAsJson(result) must beEqualTo(Json.arr())
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

    // PUT /flights/:id

    "fail with 404 Not found when changing the status of a non existing flight" in new WithApplication {
      val result = route(FakeRequest(PUT, "/flights/FAKEID").withBody("ONTIME")).get
      status(result) must equalTo(NOT_FOUND)
    }

    "fail with 400 Bad request when changing the status of a flight with an empty request body" in new WithApplication {
      val result = route(FakeRequest(PUT, "/flights/IB001")).get
      status(result) must equalTo(BAD_REQUEST)
    }

    "fail with 400 Bad request when changing the status of a flight with a wrong value" in new WithApplication {
      val result = route(FakeRequest(PUT, "/flights/IB001").withBody("FAKESTATUS")).get
      status(result) must equalTo(BAD_REQUEST)
    }

    "update the flight status" in new WithApplication {
      val result = route(FakeRequest(PUT, "/flights/IB001").withBody("CANCELLED")).get
      status(result) must equalTo(OK)
      contentAsString(result) must beEmpty
      val result2 = route(FakeRequest(GET, "/flights")).get
      status(result2) must equalTo(OK)
      val flight = contentAsJson(result2).asOpt[List[JsObject]].getOrElse(List())
                    .filter(f => (f \ "id").as[JsString].value == "IB001").headOption.getOrElse(Json.obj())
      (flight \ "status").asOpt[String].getOrElse("") must beEqualTo("CANCELLED")
    }

    // DELETE /flights/:id

    "fail with 404 Not found when deleting a non existing flight" in new WithApplication {
      val result = route(FakeRequest(DELETE, "/flights/FAKEID")).get
      status(result) must equalTo(NOT_FOUND)
    }

    "delete a flight" in new WithApplication {
      val result = route(FakeRequest(DELETE, "/flights/IB001")).get
      status(result) must equalTo(OK)
      val result2 = route(FakeRequest(DELETE, "/flights/IB001")).get
      status(result2) must equalTo(NOT_FOUND)
    }
  }
}
