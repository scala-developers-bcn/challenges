package net.pdiaz.flyapp.test

import net.pdiaz.flyapp.stores._
import java.util.Date
import scala.collection._
import com.github.nscala_time.time.Imports._
import org.specs.Specification

class StoreSpec extends Specification {
  "store.list(..)" should {

    "be empty if previous object is stored" in {
      val store = new FlightMemStore

      store.list must be(Nil)
    }

    "return 1 element if 1 is inserted" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight)

      store.list must contain("1", flight)
    }

    "return 0 element if 1 is inserted in the past" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.yesterday, DateTime.yesterday, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight)

      store.list must be(Nil)
    }
  }

  "store.store(...)" should {
    "return StoreDone if success" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
    }

    "return DataAlreadyExists if is already" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.store("1", flight) must be(DataAlreadyExists)
    }
  }

  "store.delete(...)" should {
    "return DataNotFound on empty store" in {
      val store = new FlightMemStore

      store.delete("1") must be(DataNotFound)
    }

    "return ModifyDone when delete is successful" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.delete("1") must be(ModifyDone)
    }

    "return DataNotFound when delete two times the same flight" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.delete("1") must be(ModifyDone)
      store.delete("1") must be(DataNotFound)
    }

    "return DataNotFound when trying to delete a flight and there is a different one in the store" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.delete("2") must be(DataNotFound)
    }
  }
  "store.get(...)" should {
    "return None when trying to get a flight in a empty store" in {
      val store = new FlightMemStore

      store.get("1") must be(None)
    }

    "return None when trying to get a flight in a store with another flight" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.get("2") must be(None)
    }

    "return the flight when trying to get a flight that is in the store" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.get("1") must beSome(flight)
    }

    "return the flight when trying to get a flight that is in the store with more flight in it" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)
      val flight2 = Flight(DateTime.now, DateTime.now, "X2", "Y2", FlightStatus.OnTime)
      val flight3 = Flight(DateTime.now, DateTime.now, "X3", "Y3", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.store("2", flight2) must be(StoreDone)
      store.store("3", flight3) must be(StoreDone)
      store.get("1") must beSome(flight)
    }

    "return None if the flight is deleted from the store" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.now, DateTime.now, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight) must be(StoreDone)
      store.delete("1")

      store.get("1") must be(None)
    }
  }
  "store.listFilter(...)" should {
    "return future flights without filters" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight)

      store.listFilter(None, None) must contain("1", flight)
    }

    "return the flights that meet the filter (only fromFilter)" in {
      val store = new FlightMemStore
      val flightX = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)
      val flight2 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X1", "Y", FlightStatus.OnTime)
      val flight3X = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)
      val flight4 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X3", "Y", FlightStatus.OnTime)

      store.store("1", flightX)
      store.store("2", flight2)
      store.store("3", flight3X)
      store.store("4", flight4)

      val retList = store.listFilter(fromFilter = Some("X"))

      retList must have size(2)
      retList must contain("1", flightX)
      retList must contain("3", flight3X)

    }

    "return the flights that meet the filter (only toFilter)" in {
      val store = new FlightMemStore
      val flightY = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)
      val flight2 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y1", FlightStatus.OnTime)
      val flight3Y = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)
      val flight4 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y3", FlightStatus.OnTime)

      store.store("1", flightY)
      store.store("2", flight2)
      store.store("3", flight3Y)
      store.store("4", flight4)

      val retList = store.listFilter(toFilter = Some("Y"))

      retList must have size(2)
      retList must contain("1", flightY)
      retList must contain("3", flight3Y)

    }

    "return the flights that meet the both filters" in {
      val store = new FlightMemStore
      val oceanic815 = Flight(DateTime.tomorrow, DateTime.tomorrow, "Sydney", "Los Angeles", FlightStatus.OnTime)
      val flight2 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Los Angeles", FlightStatus.OnTime)
      val flight3 = Flight(DateTime.tomorrow, DateTime.tomorrow, "Sydney", "Y2", FlightStatus.OnTime)
      val flight4 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y3", FlightStatus.OnTime)

      store.store("1", oceanic815)
      store.store("2", flight2)
      store.store("3", flight3)
      store.store("4", flight4)

      val retList = store.listFilter(fromFilter = Some("Sydney"), toFilter = Some("Los Angeles"))

      retList must have size (1)
      retList must contain("1", oceanic815)
    }

    "return List() if there isn't a flight that meet the filters" in {
      val flightX = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)
      val store = new FlightMemStore
      val flight2 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X1", "Y", FlightStatus.OnTime)
      val flight3X = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)
      val flight4 = Flight(DateTime.tomorrow, DateTime.tomorrow, "X3", "Y", FlightStatus.OnTime)

      store.store("1", flightX)
      store.store("2", flight2)
      store.store("3", flight3X)
      store.store("4", flight4)

      store.listFilter(fromFilter = Some("Y")) must be(Nil)
    }

    "return no flights if are in the past without filters" in {
      val store = new FlightMemStore
      val flight = Flight(DateTime.tomorrow, DateTime.tomorrow, "X", "Y", FlightStatus.OnTime)

      store.store("1", flight)

      store.listFilter(None, None) must contain("1", flight)
    }
  }
}
