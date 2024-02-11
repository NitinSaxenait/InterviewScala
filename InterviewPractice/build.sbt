ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val akkaVersion = "2.8.0"
val scalaTestVersion = "3.2.15"

lazy val root = (project in file("."))
  .settings(
    name := "InterviewPractice"
  )
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion,
)