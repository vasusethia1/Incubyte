name := "Incubyte"

version := "0.1"

scalaVersion := "2.12.8"

val sparkVersion = "3.1.1"
val vegasVersion = "0.3.11"
val postgresVersion = "42.2.2"

resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.2",
  "org.apache.spark" %% "spark-sql" % "3.1.2" % "provided",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  // logging
  "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.4.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  // postgres for DB connectivity
  "org.postgresql" % "postgresql" % postgresVersion
)