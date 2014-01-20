/**
 * Created by JPLOPEZ on 15/01/14.
 */

package controllers

/*import scala.concurrent.duration._
import scala.concurrent.Await
import org.junit._
import org.junit.Assert._
import org.mockito.Mockito._
import play.api.test._
import play.api.test.Helpers._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import services.FlightServiceComponent
import models._*/

/*class UserControllerTest {

  private val flightController = new FlightController with FlightServiceComponentMock {}

  @Test
  def createFlight() {
    val email = "abc@test.com"
    val request = FakeRequest(POST, "/flights")
      .withBody(
        Json.obj(
          "email" -> email
        )
      )

    val hey: Result = flightController.createFlight(request)

    assertEquals(201, status(hey))
    verify(userController.userService).createUser(User(Option.empty, email))
  }

  @Test
  def updateUser() {
    val id = 1
    val request = FakeRequest(PUT, s"/users/$id")
      .withBody(
        Json.obj(
          "email" -> "abc@test.com"
        )
      )

    val hey: Result = userController.createUser(request)

    assertEquals(201, status(hey))
  }

}

trait FlightServiceComponentMock extends FlightServiceComponent {

  override val fligthService = mock(classOf[FlightService])

}*/
