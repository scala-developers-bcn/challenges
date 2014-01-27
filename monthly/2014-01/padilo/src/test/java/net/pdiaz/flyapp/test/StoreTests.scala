package net.pdiaz.flyapp.test

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import net.pdiaz.flyapp.stores.FlightMemStore
import net.pdiaz.flyapp.stores.FlightMemStore
import net.pdiaz.flyapp.stores.Flight
import org.joda.time.DateTime
import net.pdiaz.flyapp.stores.FlightStatus
import net.pdiaz.flyapp.stores.StoreDone
import net.pdiaz.flyapp.stores.DataAlreadyExists
import net.pdiaz.flyapp.stores.DataNotFound
import net.pdiaz.flyapp.stores.ModifyDone

class StoreTests extends FlatSpec with ShouldMatchers {
  "store.list(..)" should "be empty if previous object is stored" in {
    val store = new FlightMemStore

    store.list should be(Nil)
  }

  it should "return 1 element if 1 is inserted" in {
    val store = new FlightMemStore
    val flight = Flight(DateTime.now().plusDays(1), DateTime.now().plusDays(1), "X", "Y", FlightStatus.OnTime)

    store.store("1", flight)

    store.list should contain("1", flight)
  }

  it should "return 0 element if 1 is inserted in the past" in {
    val store = new FlightMemStore
    val flight = Flight(DateTime.now().minusDays(1), DateTime.now().minusDays(1), "X", "Y", FlightStatus.OnTime)

    store.store("1", flight)

    store.list should be(Nil)
  }

  "store.store(...)" should "return StoreDone if success" in {
    val store = new FlightMemStore
    val flight = Flight(DateTime.now(), DateTime.now(), "X", "Y", FlightStatus.OnTime)

    store.store("1", flight) should be(StoreDone)
  }

  it should "return DataAlreadyExists if is already" in {
    val store = new FlightMemStore
    val flight = Flight(DateTime.now(), DateTime.now(), "X", "Y", FlightStatus.OnTime)

    store.store("1", flight) should be(StoreDone)
    store.store("1", flight) should be(DataAlreadyExists)
  }

  "store.delete(...)" should "return DataNotFound on empty store" in {
    val store = new FlightMemStore

    store.delete("1") should be(DataNotFound)
  }

  it should "return ModifyDone when delete is succesful" in {
    val store = new FlightMemStore
    val flight = Flight(DateTime.now(), DateTime.now(), "X", "Y", FlightStatus.OnTime)

    store.store("1", flight) should be(StoreDone)
    store.delete("1") should be(ModifyDone)
  }

}