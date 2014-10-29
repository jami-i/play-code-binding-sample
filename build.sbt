import play.PlayScala
import sbt.Keys._

organization := "org.jami"

name := """play-code-binding-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

