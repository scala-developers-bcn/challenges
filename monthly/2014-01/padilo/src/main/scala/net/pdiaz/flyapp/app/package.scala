package net.pdiaz.flyapp

import unfiltered.response.ResponseString
import net.pdiaz.flyapp.stores.Flight
import net.pdiaz.flyapp.stores.FlightStatus
import org.joda.time.format.ISODateTimeFormat

package object app {
  implicit def string2ResponseString(s: String) = ResponseString(s)

  import argonaut._, Argonaut._
  import scalaz._, Scalaz._

  val fmt = ISODateTimeFormat.dateTime

  implicit val FlightCodecJson: EncodeJson[Flight] = EncodeJson(f =>
      ("arrival" := fmt.print(f.arrival)) ->:
      ("departure" := fmt.print(f.departure)) ->:
      ("from" := f.from) ->:
      ("to" := f.to) ->:
      ("status" := f.status.toString) ->:
      jEmptyObject)

  implicit val FlightDecodecJson: DecodeJson[Flight] = DecodeJson(f => for {
    arrival <- (f --\ "arrival").as[String]
    departure <- (f --\ "departure").as[String]
    from <- (f --\ "from").as[String]
    to <- (f --\ "to").as[String]
    status <- (f --\ "status").as[String]
  } yield Flight(fmt.parseDateTime(arrival), fmt.parseDateTime(departure), from, to, FlightStatus.withName(status)))

    implicit val FlightPairCodecJson: EncodeJson[(String,Flight)] = EncodeJson(pair => {
      def key = pair._1
      def f = pair._2
      ("id" := key) ->:
      ("arrival" := fmt.print(f.arrival)) ->:
      ("departure" := fmt.print(f.departure)) ->:
      ("from" := f.from) ->:
      ("to" := f.to) ->:
      ("status" := f.status.toString) ->:
      jEmptyObject
})

}