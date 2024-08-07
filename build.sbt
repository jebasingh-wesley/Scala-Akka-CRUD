ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

//lazy val root = (project in file("."))
//  .settings(
//    name := "untitled"
//  )


lazy val root = (project in file("."))
  .settings(
    name := "untitled",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % "2.6.20",
      "com.typesafe.akka" %% "akka-stream" % "2.6.20",
      "com.typesafe.akka" %% "akka-http" % "10.2.9",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.9"
    )
  )