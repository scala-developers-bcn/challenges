package net.pdiaz.flyapp.app

import unfiltered.request._
import unfiltered.response._
import unfiltered.directives._
import unfiltered.directives.Directives._

//** unfiltered plan */
class App extends unfiltered.filter.Plan {

  def intent = {
    case GET(Path(Seg(Nil))) => {
      Ok ~> ResponseString("Welcome\n\nThis is under construction no services are implemented but are published :)\n\nDownload chrome postman at https://chrome.google.com/webstore/detail/postman-rest-client-packa/fhbjgbiflinjbdggehcddcbncdddomop\n\nAnd import https://www.getpostman.com/collections/785dcb9f5a081a1caea4")
    }
    case POST(Path(Seg("flight" :: Nil))) => {
      Ok ~> ResponseString("POST a flight (id, arrival, departure, from, to, status)")
    }
    case GET(Path(Seg("flight" :: Nil))) => {
      Ok ~> ResponseString("GET a list of flights to X [within time]\nGET a list of flights from Y [within time]")
    }
    case PUT(Path(Seg("flight" :: id :: Nil))) => {
      Ok ~> ResponseString(s"PUT an updated status to a flight (i.e: DELAYED, CANCELLED, Gate 42) [id: ${id}]")
    }
    case DELETE(Path(Seg("flight" :: id :: Nil))) => {
      Ok ~> ResponseString(s"DELETE a flight (maybe) [id: ${id}]")
    }
    case _ => {
      NotFound ~> ResponseString("Nice try, but this is not found ;-)")
    }
  }
}

object Server {
  def main(args: Array[String]) {
    unfiltered.jetty.Http(8080).plan(new App).run({ svr =>
      unfiltered.util.Browser.open(svr.url)
    }, { svr =>
    })
  }
}
