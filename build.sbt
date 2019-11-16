name := "category-theory-for-programmers-scala"

version := "0.1"

scalaVersion := "2.12.4"

lazy val global = project
  .in(file("."))
  .settings(settings)
  .aggregate(
    chapter2,
    chapter4,
    chapter5
  )

lazy val chapter2 = project
  .settings(
    name := "chapter-2",
    settings,
    libraryDependencies ++= commonDependencies
  )

lazy val chapter4 = project
  .settings(
    name := "chapter-4",
    settings,
    libraryDependencies ++= commonDependencies
  )

lazy val chapter5 = project
  .settings(
    name := "chapter-5",
    settings,
    libraryDependencies ++= commonDependencies
  )
// DEPENDENCIES

lazy val dependencies =
  new {
    val scalatestV  = "3.0.4"
    val scalacheckV = "1.13.5"
    val scalatest   = "org.scalatest" %% "scalatest" % scalatestV
    val scalacheck  = "org.scalacheck" %% "scalacheck" % scalacheckV
  }

lazy val commonDependencies = Seq(
  dependencies.scalatest  % "test",
  dependencies.scalacheck % "test"
)

// SETTINGS

lazy val settings =
commonSettings ++
scalafmtSettings

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
    scalafmtTestOnCompile := true,
    scalafmtVersion := "1.2.0"
  )

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)
