package models
 
import java.util.Date
import org.squeryl.{Schema, KeyedEntity}
import play.api.db._
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.adapters.H2Adapter
import play.api.Play.current

case class Task(val description: String, val due: Date) extends KeyedEntity[Long] {
  val id: Long = 0 
}

object AppDB extends Schema {
  val tasks = table[Task]("task")
}

