package pageobjects.videogame

import io.gatling.core.Predef._
import io.gatling.http.Predef._


import scala.util.Random

object VideoGames {

  val feederUsernames = csv("testdata/username.csv").circular
  val feederPasswords = csv("testdata/password.csv").circular
  val feedGameIds = csv("testdata/videogame_id.csv").circular


  val createHeader = Map(
    "Content-Type" -> "application/json",
    "Authorization" -> "Bearer ${token}")

  val createHeader02 = Map(
    "Content-Type" -> "application/json",
    "accept" -> "application/json",
    "Authorization" -> "Bearer ${token}")

  val updateHeader = Map(
    "Content-Type" -> "application/json",
    "Authorization" -> "Bearer ${token}"
  )

  val deleteHeader = Map(
    "Content-Type" -> "application/json",
    "accept" -> "application/json",
    "Authorization" -> "Bearer ${token}")

  val videoGamesDataFeeder = Iterator.continually(Map(
    "name" -> java.util.UUID.randomUUID.toString(),
    "reviewScore" -> Random.nextInt(Integer.max(0, 99))
  ))

  val feederCategory = csv("testdata/category.csv").circular
  val feederRating = csv("testdata/rating.csv").circular

  def GetAllVideoGame =  {
      exec(http("Get All Video Games")
        .get("/api/videogame")
        .headers(createHeader)
        .check(status.is(200))
      )
  }

  def CreateVideoGame = {
    feed(videoGamesDataFeeder)
      .feed(feederRating)
      .feed(feederCategory)
    .exec(http("Create Video Game")
      .post("/api/videogame")
      .headers(createHeader02)
      .body(ElFileBody("json/createVideoGame.json"))
      .check(status.is(200))
    )
  }

  def GetVideoGameById = {
    feed(feedGameIds)
      .exec(http("Get Video Game By ID")
        .get("/api/videogame/${id}")
        .headers(createHeader02)
        .check(status.is(200))
      )
  }

  def updateVideoGame = {
    feed(feedGameIds)
      .exec(http("Update Video Game")
        .put("/api/videogame/${id}")
        .headers(updateHeader)
        .body(ElFileBody("json/updateVideoGame.json"))
        .check(status.is(200))
      )
  }


  def deleteVideoGame = {
    feed(feedGameIds)
      .exec(http("Delete Video Game")
        .delete("/api/videogame/${id}")
        .headers(deleteHeader)
        .check(status.is(200))
      )
  }



}
