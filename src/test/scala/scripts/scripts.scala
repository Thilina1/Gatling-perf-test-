package scripts

import pageobjects.videogame
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object scripts {

 object Auth {
  def Authentication = {
   exec(videogame.Authentication.createAuthentication)
  }
 }

 object VideoGames
  {
   def GetAllVideoGames = {
    exec(videogame.VideoGames.GetAllVideoGame)
   }

   def CreateVideoGame = {
    exec(videogame.VideoGames.CreateVideoGame)
   }

   def GetVideoGameById = {
    exec(videogame.VideoGames.GetVideoGameById)
   }

   def updateVideoGame = {
    exec(videogame.VideoGames.updateVideoGame)
   }

   def deleteVideoGame = {
    exec(videogame.VideoGames.deleteVideoGame)
   }

  }


  def VideoGameFlow(paceValue:Integer,duration:Integer) = scenario("VideoGameFlow")
    .exec(Auth.Authentication)
    .during(duration.minutes) {
     pace(paceValue)
       .exec(VideoGames.GetAllVideoGames)
       .exec(VideoGames.CreateVideoGame)
       .exec(VideoGames.GetVideoGameById)
       .exec(VideoGames.updateVideoGame)
       .exec(VideoGames.deleteVideoGame)
    }
}
