import play.Project._

name := "FlyApp"

version := "0.0.1-SNAPSHOT"

playScalaSettings

javaOptions in Test += "-Dconfig.resource=application-test.conf"
