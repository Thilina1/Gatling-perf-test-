package pageobjects.videogame

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Authentication {

  val feederUsernames = csv("testdata/username.csv").circular
  val feederPasswords = csv("testdata/password.csv").circular

  val createHeader = Map(
    "Content-Type" -> "application/json",
    "Accept" -> "*/*")

  def createAuthentication = {
    feed(feederUsernames)
      .feed(feederPasswords)
      .exec(http("Authentication token request")
        .post("/api/authenticate")
        .body(ElFileBody("json/authentication.json"))
        .headers(createHeader)
        .check(regex("""\{"token":"(.*?)"""").saveAs("token"))

      )

  }


}
