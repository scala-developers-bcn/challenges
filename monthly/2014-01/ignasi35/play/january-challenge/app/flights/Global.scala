package flights

import controllers.Application
import play.GlobalSettings
import repositories.InMemFlightsRepository

/*
 * This inheritance is horrible!
 * play.GlobalSettings is a class I have to extend so that run 
 * works while play.api.GlobalSettings is a trait I'm mixing it
 * so that I can inject a FakeInstance in my tests by extending
 * this class and overriding/mocking what I need for testing.
 */
class Global extends GlobalSettings with play.api.GlobalSettings {

  def flightRepo = new InMemFlightsRepository
  def flightCtlr = new Application(flightRepo)

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    flightCtlr.asInstanceOf[A]
  }
}