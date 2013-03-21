package app.models;

import org.squeryl.KeyedEntity
import org.squeryl.Schema


class AppDbObject extends KeyedEntity[Long] {
	var id: Long = 0
}
object AppDb extends Schema {
  val tasks = table[Task]
  override def drop = super.drop
}