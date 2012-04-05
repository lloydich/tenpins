name := "Tenpin Bowling"
 
version := "0.1"
 
scalaVersion := "2.9.1"
 
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
   "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",
    "org.clapper" %% "grizzled-slf4j" % "0.6.6"
)

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"
