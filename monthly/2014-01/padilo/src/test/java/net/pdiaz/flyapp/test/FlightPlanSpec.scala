package net.pdiaz.flyapp.test

import org.specs.Specification
import net.pdiaz.flyapp.stores._
import net.pdiaz.flyapp.app._
import com.github.nscala_time.time.Imports._
import unfiltered.spec.jetty.Served
import scala.language.postfixOps
import org.eclipse.jetty.http.HttpStatus

object MockFlightStore extends FlightStore {
  def listFilter(toFilter: Option[String], fromFilter: Option[String]): List[(String, Flight)] = Nil
  def list(): List[(String, Flight)] = Nil
  def store(key: String, flight: Flight): StoreResult = StoreDone
  def update(key: String, flight: Flight): ModifyResult = ModifyDone
  def delete(key: String): ModifyResult = ModifyDone
}

class FlightPlanSpec extends Specification with Served {
  import dispatch.classic._

  def setup = _.plan(new FlightPlan(MockFlightStore))

  val http = new Http

  "if not found url is given" should {
    "return 404" in {
      val statusCode = http x (host / "not_exists_url" as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (404)
    }
  }

  def acceptJSON = Map("Accept" -> "application/json")
  def acceptXML = Map("Accept" -> "application/xml")
  def contentJSON = Map("Content-Type" -> "application/json")

  val oceanic815 = """
    {
	  "to": "Los Angeles",
	  "status": "OnTime",
	  "from": "Sydney",
	  "departure": "2014-05-24T12:30:00.488+01:00",
	  "arrival": "2014-05-24T13:45:00.488+01:00"
  	}
    """

  "if client doesn't accept json" should {
    s"[TODO] return ${HttpStatus.NOT_ACCEPTABLE_406} for /flight/1 POST" in {
      val statusCode = http x (host / "flights" / "1" << Map() <:< acceptXML as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.NOT_ACCEPTABLE_406)
    }
    s"return ${HttpStatus.NOT_ACCEPTABLE_406} for /flight GET" in {
      val statusCode = http x (host / "flights" <:< acceptXML as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.NOT_ACCEPTABLE_406)
    }
    s"[TODO] return ${HttpStatus.NOT_ACCEPTABLE_406} for /flight PUT" in {
      val statusCode = http x (host / "flights" / "1" <<< Map() <:< acceptXML as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.NOT_ACCEPTABLE_406)
    }
    s"return ${HttpStatus.NOT_ACCEPTABLE_406} for /flight/1 DELETE" in {
      val statusCode = http x (host.DELETE / "flights" / "1" <:< acceptXML as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.NOT_ACCEPTABLE_406)
    }
  }
  "if client does an unsupported request" should {
    s"return ${HttpStatus.METHOD_NOT_ALLOWED_405} for /flight/1 DELETE" in {
      val statusCode = http x (host.PUT / "flights" <:< acceptJSON as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.METHOD_NOT_ALLOWED_405)
    }

  }
  "if client does a GET request to /flight and accepts JSON" should {
    s"return ${HttpStatus.OK_200}" in {
      val statusCode = http x (host / "flights" <:< acceptJSON as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.OK_200)
    }
  }
  "if client does a DELETE request to /flight/1 and accepts JSON" should {
    s"return ${HttpStatus.NO_CONTENT_204}" in {
      val statusCode = http x (host.DELETE / "flights" / "1" <:< acceptJSON as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.NO_CONTENT_204)
    }
  }
  "if client does a PUT request to /flight/1, accepts JSON and sends a JSON" should {
    s"return ${HttpStatus.NO_CONTENT_204}" in {
      val statusCode = http x (host.PUT / "flights" / "1" <:< acceptJSON <:< contentJSON <<< oceanic815 as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.NO_CONTENT_204)
    }
  }
  "if client does a POST request to /flight/1, accepts JSON and sends a JSON" should {
    s"return ${HttpStatus.NO_CONTENT_204}" in {
      val statusCode = http x (host / "flights" / "1" <:< acceptJSON <:< contentJSON << oceanic815 as_str) {
        case (statusCode, _, _, _) => statusCode
      }
      statusCode must_== (HttpStatus.CREATED_201)
    }
  }

}
