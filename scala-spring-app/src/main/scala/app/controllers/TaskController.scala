package app.controllers

import java.lang.Long
import java.util.Date

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable

import app.models.Task


@Controller
class TaskController {

	def simpleAction = {
	  
	}
  
	def create(uiModel: Model) = {
		var task = new Task
		task.setDescription("Write a bunch of stuff")
		task.setDueDate(new Date)
		task.insert         
		uiModel.addAttribute("task", task)
	}

	def edit(@PathVariable id: Long, uiModel: Model):String = {
		val task = Task.find(id)
		task.setDescription(task.getDescription + " *edited")
		task.update
		uiModel.addAttribute("task", task)
		return "task/edit"
	}

	def show(@PathVariable id: Long, uiModel: Model):String = {
		val task = Task.find(id)
		uiModel.addAttribute("task", task)
		return "task/show"
	}

	def list(uiModel: Model) = {
	    var tasks = Task.all
	    uiModel.addAttribute("tasks", tasks)
	}
}