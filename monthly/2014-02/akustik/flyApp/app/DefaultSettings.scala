import play.api.GlobalSettings
import play.api.Logger
import controllers.Flights
import repositories._
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration.Duration
import play.api.mvc.RequestHeader
import play.api.mvc.Results._
import play.api.libs.json.Json
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object DefaultSettings extends GlobalSettings {
  
  val timeout = Duration(10000, "millis")

  val flightsRepo = new MongoDBFlightsRepository
  
  val flightsController = new Flights(flightsRepo)

  val classOfFlights = classOf[Flights]
  
  def responseExecutionError(msg: String) = Ok(Json.obj("result" -> "failure", "detail" -> msg))

  override def onStart(app: play.api.Application) {
    Logger.info("Setting up indexes...")
    Await.result(flightsRepo.ensureIndexes, timeout)
    Logger.info("Indexes created")
  }
  
  override def onError(request: RequestHeader, ex: Throwable) = {
    Logger.error("Execution error", ex)
    Future {
      Ok(Flights.jsonExecutionError(ex.getMessage()))
    }
  }  
  
  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    controllerClass match {
      case `classOfFlights` => flightsController.asInstanceOf[A]
      case _ => {
        throw new IllegalArgumentException(s"Controller of class ${controllerClass} not available")
      }
    }
  }

}