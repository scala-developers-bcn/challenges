package model

case class Flight(id: String, from: String, to: String,
  arrival: Long, departure: Long, status: String)