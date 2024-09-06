package test

import scripts.scripts._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

class LoadTest extends BaseTest("loadTest") {

  setUp(
    VideoGameFlow(getPace("LoadProfile"),TESTDURATION)
      .inject(rampUsers(getUsers("LoadProfile"))during(RAMPUPLONG.minutes))
  ).maxDuration(TESTDURATION.minutes).protocols(httpsProtocol)

}
