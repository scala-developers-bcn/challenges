import play.api.libs.json._
import repositories.InMemoryFlightsRepository
import repositories.RecordingFlightsRepository

trait FlightData {

  val flight1 = Json.obj(
    "id" -> "JK1234",
    "from" -> "BERLIN",
    "to" -> "BCN",
    "arrival" -> 1390137085,
    "departure" -> 1390137000,
    "status" -> "ON TIME")
    
  val incompleteFlight = Json.obj("id" -> "JK1234")
}