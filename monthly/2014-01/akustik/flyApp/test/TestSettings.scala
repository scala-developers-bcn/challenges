import org.specs2.execute.AsResult
import org.specs2.mutable.Around

import controllers.Flights
import play.api.GlobalSettings
import play.api.Logger
import play.api.test.FakeApplication
import play.api.test.Helpers.running
import repositories.FlightsRepository
import repositories.RecordingFlightsRepository

class TestSettings(flightsRepo: FlightsRepository) extends GlobalSettings {

  val classOfFlights = classOf[Flights]

  val flightsController = new Flights(flightsRepo)

  override def onStart(app: play.api.Application) {
    Logger.info("Init test settings")
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