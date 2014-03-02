import play.Project._

name := "FlyApp"

version := "0.0.1-SNAPSHOT"

playScalaSettings

javaOptions in Test += "-Dconfig.resource=application-test.conf"

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.2"
)
