scalaVersion := "2.13.12"

javacOptions ++= Seq(
  "-source", "21",
  "-target", "21"
)

compileOrder := CompileOrder.Mixed

Compile / scalaSource := baseDirectory.value / "src/scala/"
Compile / javaSource := baseDirectory.value / "src/java/"

import scala.sys.process._

lazy val cleanupTask = taskKey[Unit]("Now we will pretend we are Ant")

import scala.sys.process._

cleanupTask := ("rm -fr ./bin" #&& "cp -r ./target/scala-2.13/classes ./bin" !!)

compile in Compile := Def.taskDyn {
  val result = (compile in Compile).value
  Def.task {
    val _ = cleanupTask.value
    result
  }
}.value
