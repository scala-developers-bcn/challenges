package net.pdiaz.flyapp.app

import unfiltered.request._
import unfiltered.response._
import unfiltered.directives._
import unfiltered.directives.Directives._
import net.pdiaz.flyapp.stores.FlightMemStore
import net.pdiaz.flyapp.stores.{ Flight, FlightStore, DataAlreadyExists, StoreDone, DataNotFound, ModifyDone }
import argonaut.integrate.unfiltered._
import argonaut._
import Argonaut._
import scalaz._
import Scalaz._
import com.github.nscala_time.time.Imports._
import scala.language.implicitConversions

//** unfiltered plan */
class FlightPlan(flightStore: FlightStore = new FlightMemStore) extends unfiltered.filter.Plan {
  
  flightStore.putDefaultData()

  def ensureContentType(tpe: String) =
    when { case RequestContentType(`tpe`) => } orElse UnsupportedMediaType

  def ensureJSONContentType = ensureContentType("application/json")

  def intent = Directive.Intent.Path {
    case Seg(List("flights")) => {
      def get = for {
        _ <- GET
        _ <- Accepts.Json
        toOption <- data.as.Option[String] named "to"
        fromOption <- data.as.Option[String] named "from"
      } yield {
        Ok ~> JsonResponse(flightStore.listFilter(toOption, fromOption))
      }

      get
    }
    case Seg(List("flights", key)) => {
      def put = for {
        _ <- PUT
        _ <- Accepts.Json
        _ <- ensureJSONContentType
        r <- request[Any]
      } yield {
        Body.string(r).decodeOption[Flight] match {
          case Some(flight) => {
            flightStore.update(key, flight) match {
              case DataNotFound => {
                NotFound
              }
              case ModifyDone => {
                NoContent
              }
            }
          }
          case None => {
            BadRequest
          }
        }
      }

      def post = for {
        _ <- POST
        _ <- Accepts.Json
        _ <- ensureJSONContentType
        r <- request[Any]
      } yield {
        Body.string(r).decodeOption[Flight] match {
          case Some(flight) => {
            flightStore.store(key, flight) match {
              case DataAlreadyExists => {
                Conflict
              }
              case StoreDone => {
                Created
              }
            }
          }
          case None => {
            BadRequest
          }
        }

      }

      def delete = for {
        _ <- DELETE
        _ <- Accepts.Json
      } yield {
        flightStore.delete(key) match {
          case DataNotFound => {
            NotFound
          }
          case ModifyDone => {
            NoContent
          }
        }
      }

      put | delete | post
    }
  }
}

class HomePagePlan extends unfiltered.filter.Plan {
  def intent = {
    case Path("/") => {
      Ok ~> Html5(
        <html>
          <head><title>Pablo monthly Scala</title></head>
          <body>
    		  <p>
    		  	Welcome
    		  </p>
    		  <p>
    		  	This is under construction but nearly finish, I need to investigate a bit how to test this :)
    		  	To try this API, download chrome postman <a href="https://chrome.google.com/webstore/detail/postman-rest-client-packa/fhbjgbiflinjbdggehcddcbncdddomop">HERE</a>
    		  </p>
    		  <p>
    		  	And import <a href="https://www.getpostman.com/collections/cb088452d39f131e4b7f">this</a>
    		  </p>
    		  <p>
    		  	If you want to play by yourself here you have the /flights resource at http://127.0.0.1/flights with some data
    		  </p>
    		  <ul>
    		  	<li>POST /flights/[id]: To create a new resource path parameter id</li>
    		  	<li>PUT /flights/[id]: To change an already existing resource with path parameter id</li>
    		  	<li>DELETE /flights/[id]: To delete an already existing resource with path parameter id</li>
    		  	<li>GET /flights: To retrieve all the flights within time, also accepts to do queries usign URL params (to,from) to filter the data</li>
    		  </ul>
    		  <hr />
    		  Pablo D&iacute;az
          </body>
        </html>)
    }
  }
}

object Server {
  def main(args: Array[String]) {
    unfiltered.jetty.Http(8080).plan(new HomePagePlan).plan(new FlightPlan).run()
  }
}
