organization := "net.pdiaz"

name := "flyapp"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-directives" % "0.7.1",
  "net.databinder" %% "unfiltered-filter" % "0.7.1",
  "net.databinder" %% "unfiltered-jetty" % "0.7.1",
  "io.argonaut" %% "argonaut" % "6.0.1" ,
  "io.argonaut" %% "argonaut-unfiltered" % "6.0",
  "com.github.nscala-time" %% "nscala-time" % "0.6.0",
  "net.databinder" %% "unfiltered-spec" % "0.7.1" % "test"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2",
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/release"  
)
