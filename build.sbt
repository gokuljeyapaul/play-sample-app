name := "catalog-product"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
  "net.vz.mongodb.jackson" % "mongo-jackson-mapper" % "1.4.2",
  "com.google.code.gson" % "gson" % "2.3.1"
)
