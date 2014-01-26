package repositories

import org.specs2.mutable.Specification
import controllers.FlightsFixtures

class FlightsRepositorySpec extends Specification with FlightsFixtures {

  "A FlightsRepository" should {
    "store fligths added" in {
      val repo: FlightsRepository = new InMemFlightsRepository
      repo.loadAll must equalTo(List())
      repo.add(flightFixture001)
      repo.loadAll must equalTo(List(flightFixture001))

    }
  }
}