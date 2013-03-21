package app.models;

import java.util.Date

import scala.beans.BeanProperty
import scala.collection.JavaConversions.seqAsJavaList

import org.squeryl.PrimitiveTypeMode.from
import org.squeryl.PrimitiveTypeMode.long2ScalarLong
import org.squeryl.PrimitiveTypeMode.select
import org.squeryl.PrimitiveTypeMode.transaction
import org.squeryl.PrimitiveTypeMode.where

import AppDb.tasks


class Task extends AppDbObject {
	@BeanProperty
	var description: String = ""
	@BeanProperty
	var dueDate: Date = new Date

	override def toString: String = {
		"<td>" + this.id + "</td><td>" + this.description + "</td><td>" + this.dueDate + "</td>";
	}
	def insert = transaction {
	 tasks.insert(this)
	}
	def update = transaction {
	 tasks.update(this)
	}
}
object Task {
  def all = seqAsJavaList(transaction { from(tasks)(s => select(s)).toList })
  def find(id:Long):Task = transaction {
    from(tasks)(s => where(s.id === id) select(s)).last
  }
}