import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object Deps {
  val V = new {
    val munit = "1.0.0-M3"
    val circe = "0.15.0-M1"
    val CE = "3.3.11"
    val http4s = "1.0.0-M30"
  }

  val circe = Def.setting(
    Seq(
      "io.circe" %%% "circe-core" % V.circe,
      "io.circe" %%% "circe-generic" % V.circe
    )
  )
  val CE = Def.setting(
    Seq(
      "org.typelevel" %%% "cats-effect" % V.CE
    )
  )
  val http4s = Def.setting(
    Seq(
      "org.http4s" %%% "http4s-core" % V.http4s,
      "org.http4s" %%% "http4s-dsl" % V.http4s,
      "org.http4s" %%% "http4s-ember-client" % V.http4s,
      "org.http4s" %%% "http4s-circe" % V.http4s
    )
  )
  val munit = Def.setting(
    Seq(
      "org.scalameta" %%% "munit" % V.munit % Test
    )
  )
  val circeParser = Def.setting(
    Seq(
      "io.circe" %%% "circe-parser" % V.circe % Test
    )
  )
}
