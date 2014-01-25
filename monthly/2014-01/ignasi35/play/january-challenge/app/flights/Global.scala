package flights

import controllers.Application
import controllers.InMemFlightsRepository
import play.GlobalSettings

class Global extends GlobalSettings {

  def flightRepo = new InMemFlightsRepository
  def flightCtlr = new Application(flightRepo)

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    flightCtlr.asInstanceOf[A]
  }
}