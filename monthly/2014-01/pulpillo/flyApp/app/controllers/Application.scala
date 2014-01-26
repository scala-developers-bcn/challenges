package controllers

import play.api._
import play.api.mvc._
import controllers.FlightsController
import services.FlightServiceComponentImpl
import repositories.FlightRepositoryComponentImpl

import views._

object Application extends FlightsController
  with FlightServiceComponentImpl
  with FlightRepositoryComponentImpl{

  // -- Actions

  /**
   * Home page
   */
  def index = Action {
    Ok(html.index("Flights app v1.0 is ready"))
  }

}
