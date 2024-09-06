package test


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.typesafe.config._


class BaseTest (val configName: String) extends Simulation{

  val video_game_server = "videogamedb.uk:443"
  val httpsProtocol = http.baseUrl("https://" + video_game_server)

  val config = ConfigFactory.load("properties.conf").getConfig(configName)

  val RAMPUPLONG: Int = getProperty("ramp_duration_long","1").toInt
  val RAMPUPSHORT: Int = getProperty("ramp_duration_short","1").toInt
  val TESTDURATION: Int = getProperty("duration","1").toInt

  def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def getUsers(path: String) = getConfProperty(path,"users")
  def getPace(path: String) = getConfProperty(path, "pace")

  def getConfProperty(path: String, property: String): Int = {
    config.getConfig(path).getInt(property)
  }

}
