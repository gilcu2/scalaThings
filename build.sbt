name := "janJulio"

version := "1.0"

scalaVersion := "2.12.1"


libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.2"

val monocleVersion = "1.4.0"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
)

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor_2.11
libraryDependencies += "com.typesafe.akka" % "akka-actor_2.12" % "2.5.3"
