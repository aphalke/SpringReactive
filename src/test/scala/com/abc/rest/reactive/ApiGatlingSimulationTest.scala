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
      http("Events-API")
        .get("http://localhost:8080/events/1")
        .header("Content-Type", "application/json")
        .check(status.is(200))
    ).pause(Duration.apply(5, TimeUnit.MILLISECONDS))
  }.repeat(1000, "n") {
    exec(
      http("GeEvents-API")
        .get("http://localhost:8080/events/${n}")
        .check(status.is(200))
    )
  }

  setUp(scn.inject(atOnceUsers(10000))).maxDuration(FiniteDuration.apply(2, "minutes"))
}