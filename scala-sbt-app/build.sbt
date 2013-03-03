name := "scala-sbt-app"

//settings.usejavacp.value = true

scalaVersion := "2.9.1"

version := "1.0"

resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

resolvers += Resolver.url("my-test-repo", new URL("http://repo1.maven.org/maven2/"))( Patterns("[organisation]/[module]_2.9.1_0.12/[revision]/giter8-scaffold-0.5.3.pom") )

libraryDependencies += "net.databinder.giter8" % "giter8_2.9.1" % "0.5.3"

//libraryDependencies += "net.databinder.giter8" % "giter8-plugin_2.9.1_0.11.3" % "0.4.5"

//libraryDependencies += "net.databinder.giter8" % "giter8-scaffold" % "0.5.2"

addSbtPlugin("net.databinder.giter8" % "giter8-scaffold" % "0.5.3")

//libraryDependencies += "net.databinder.giter8" % "giter8-scaffold" % "0.5.3"
