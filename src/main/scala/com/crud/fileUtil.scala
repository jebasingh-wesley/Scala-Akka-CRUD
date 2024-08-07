package com.crud

import scala.io.Source
import java.io.{File, PrintWriter}

final case class Person(name: String, age: Int)

object FileUtil {
  val fileName = "data.txt"

  def readFile(): Seq[Person] = {
    val source = Source.fromFile(fileName)
    val persons = source.getLines().map { line =>
      val Array(name, age) = line.split(",")
      Person(name.trim, age.trim.toInt)
    }.toSeq
    source.close()
    persons
  }

  def writeFile(persons: Seq[Person]): Unit = {
    val writer = new PrintWriter(new File(fileName))
    persons.foreach { person =>
      writer.write(s"${person.name},${person.age}\n")
    }
    writer.close()
  }

  def addPerson(person: Person): Unit = {
    val persons = readFile() :+ person
    writeFile(persons)
  }

  def updatePerson(name: String, updatedPerson: Person): Boolean = {
    val persons = readFile()
    val updatedPersons = persons.map {
      case p if p.name == name => updatedPerson
      case p => p
    }
    if (persons.exists(_.name == name)) {
      writeFile(updatedPersons)
      true
    } else {
      false
    }
  }

  def deletePerson(name: String): Boolean = {
    val persons = readFile()
    val updatedPersons = persons.filterNot(_.name == name)
    if (persons.exists(_.name == name)) {
      writeFile(updatedPersons)
      true
    } else {
      false
    }
  }
}