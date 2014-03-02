import repositories.RecordingFlightsRepository
import repositories.FlightsRepository
import org.specs2.specification.Scope
import play.api.GlobalSettings
import repositories.InMemoryFlightsRepository
import play.api.test.FakeApplication
import play.api.test.FakeApplication
import controllers.Flights
import play.api.Logger

object TestEnv {

  private class TestSettings(flightsRepo: FlightsRepository) extends GlobalSettings {

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

  trait RecordingScope extends Scope {
    val flightRepo: RecordingFlightsRepository = new RecordingFlightsRepository
    val settings: Option[GlobalSettings] = Some(new TestSettings(flightRepo))
    val recordingApp = FakeApplication(
      withGlobal = settings,
      withoutPlugins = List("play.modules.reactivemongo.ReactiveMongoPlugin"),
      additionalConfiguration = Map("customConfigKey" -> "customConfigValue"))
  }

  trait InMemoryScope extends Scope {
    val flightRepo: FlightsRepository = new InMemoryFlightsRepository
    val settings: Option[GlobalSettings] = Some(new TestSettings(flightRepo))
    val inMemoryApp = FakeApplication(
      withGlobal = settings,
      withoutPlugins = List("play.modules.reactivemongo.ReactiveMongoPlugin"),
      additionalConfiguration = Map("customConfigKey" -> "customConfigValue"))
  }

}