val V = new {
  val scala212 = "2.12.16"
  val scala213 = "2.13.8"
  val scala31 = "3.1.2"
}

resolvers += Resolver.sonatypeRepo("snapshots")

inThisBuild(
  Seq(
    organization := "dev.i10416",
    scalaVersion := V.scala31,
    licenses := List(
      "Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")
    ),
    homepage := Some(url("https://github.com/i10416/slackapis")),
    versionScheme := Some("early-semver"),
    developers := List(
      Developer(
        "i10416",
        "110416",
        "contact.110416+slackapis@gmail.com",
        url("https://github.com/i10416")
      )
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation"
    ),
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/i10416/slackapis"),
        "scm:git@github.com:i10416/slackapis.git"
      )
    )
  )
)

lazy val root = (project in file("."))
  .settings(
    name := "slackapis",
    publish / skip := true,
    publishLocal / skip := true
  )
  .aggregate(core.projectRefs: _*)

lazy val core = (projectMatrix in file("core"))
  .settings(
    name := "slackapis-core",
    scalacOptions ++= {
      if (scalaVersion.value.startsWith("2.12")) Seq("-language:higherKinds")
      else Nil
    },
    libraryDependencies ++= Deps.circe.value ++ Deps.CE.value ++ Deps.http4s.value,
    libraryDependencies ++= Deps.munit.value ++ Deps.circeParser.value
  )
  .jvmPlatform(
    scalaVersions = Seq(V.scala212, V.scala213, V.scala31)
  )
  .jsPlatform(
    scalaVersions = Seq(V.scala212, V.scala213, V.scala31)
  )
