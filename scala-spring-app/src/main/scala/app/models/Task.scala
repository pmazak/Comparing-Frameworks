package app.models;

import java.util.Date

import scala.reflect.BeanProperty


case class Task()
{
    @BeanProperty
	var id: Long = 0
	@BeanProperty
	var description: String = ""
	@BeanProperty
	var dueDate: Date = new Date

	override def toString: String = 
	{
		"<td>" + this.id + "</td><td>" + this.description + "</td><td>" + this.dueDate + "</td>";
	}
}
