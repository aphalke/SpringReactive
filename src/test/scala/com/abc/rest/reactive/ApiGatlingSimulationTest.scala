package com.abc.rest.reactive

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

class ApiGatlingSimulationTest extends Simulation {

  val scn = scenario("GetEvents").repeat(1000, "n") {
    exec(
      http("GeEvents-Variable User")
        .get("http://localhost:8080/events/${n}")
        .check(status.is(200))
    )
  }

  setUp(scn.inject(atOnceUsers(100))).maxDuration(FiniteDuration.apply(2, "minutes"))
}