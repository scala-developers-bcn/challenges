package repositories

import org.specs2.mutable.Specification
import controllers.FlightsFixtures

class FlightsRepositorySpec extends Specification with FlightsFixtures {

  "A FlightsRepository" should {
    "store fligths I add" in {
      val repo: FlightsRepository = new InMemFlightsRepository
      repo.add(flightFixture001)
    }
  }
}