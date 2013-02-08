package app.controllers

import java.util.LinkedHashMap
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import app.models.Task
import java.util.GregorianCalendar
import java.util.Date


@Controller
@RequestMapping(Array("task/*"))
class TaskController {
	var tasks = new LinkedHashMap[String, Task]
	var current_id = 1

	@RequestMapping
	def create(uiModel: Model) = {
		var task = new Task
		task.setId(current_id)
		task.setDescription("Write a bunch of stuff")
		task.setDueDate(new Date)
		println("Ab")
		tasks.put(""+current_id, task)
		current_id += 1
		uiModel.addAttribute("task", task)
	}

	@RequestMapping(Array("edit/{id}"))
	def edit(@PathVariable id: Integer, uiModel: Model):String = {
		if (tasks.size == 0) {
			create(uiModel)
		}
		var task = tasks.get(""+id)
		task.setDescription(task.getDescription + " *edited")
		tasks.put(""+id, task)
		uiModel.addAttribute("task", task)
		return "task/edit"
	}

	@RequestMapping(Array("show/{id}"))
	def show(@PathVariable id: Integer, uiModel: Model):String = {
		if (tasks.size == 0) {
			create(uiModel)
		}
		val task = tasks.get(""+id)
		uiModel.addAttribute("task", task)
		return "task/show"
	}

	@RequestMapping
	def list(uiModel: Model) = {
		uiModel.addAttribute("tasks", tasks)
	}
}