package app.controllers

import java.lang.Integer
import java.lang.Long
import java.util.Date
import java.util.LinkedHashMap

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.squeryl.PrimitiveTypeMode.transaction

import app.models.Task


@Controller
@RequestMapping(Array("task/*"))
class TaskController {

	@RequestMapping
	def create(uiModel: Model) = {
		var task = new Task
		task.setDescription("Write a bunch of stuff")
		task.setDueDate(new Date)
		task.insert         
		uiModel.addAttribute("task", task)
	}

	@RequestMapping(Array("edit/{id}"))
	def edit(@PathVariable id: Long, uiModel: Model):String = {
		val task = Task.find(id)
		task.setDescription(task.getDescription + " *edited")
		task.update
		uiModel.addAttribute("task", task)
		return "task/edit"
	}

	@RequestMapping(Array("show/{id}"))
	def show(@PathVariable id: Long, uiModel: Model):String = {
		val task = Task.find(id)
		uiModel.addAttribute("task", task)
		return "task/show"
	}

	@RequestMapping
	def list(uiModel: Model) = {
	    var tasks = Task.all
	    uiModel.addAttribute("tasks", tasks)
	}
}