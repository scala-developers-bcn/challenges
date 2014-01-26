import play.api.GlobalSettings
import play.api.Logger
import controllers.Flights
import repositories.InMemoryFlightsRepository

object DefaultSettings extends GlobalSettings {

  val flightsRepo = new InMemoryFlightsRepository

  val flightsController = new Flights(flightsRepo)

  val classOfFlights = classOf[Flights]

  override def onStart(app: play.api.Application) {
    Logger.info("Init default settings")
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