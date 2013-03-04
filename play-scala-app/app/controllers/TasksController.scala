package controllers

import org.squeryl.PrimitiveTypeMode._
import models._
import java.util.Date
import play.api._
import play.api.mvc._
import play.api.db._

object TasksController extends Controller {
  
  def index = Action {
    var task:Task = new Task("Get Things Done!", new Date)
    transaction {
      AppDB.tasks.insert(task)
    }
    println("Inserted task")
    Ok(views.html.tasks.index("Your new application is ready.", task))
  }
  
  def list = Action {
    var task:Task = new Task("Get Things Done!", new Date)
    transaction {
      AppDB.tasks.insert(task)
      AppDB.tasks.insert(task)
      AppDB.tasks.insert(task)
    }
    var tasks = from(AppDB.tasks)_

    Ok(views.html.tasks.list(tasks))
  }
  

}