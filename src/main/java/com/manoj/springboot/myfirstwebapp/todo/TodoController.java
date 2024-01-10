package com.manoj.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

//@Controller
@SessionAttributes("name")
public class TodoController {

	
	public TodoController(TodoService todoSevice) {
		super();
		this.todoSevice = todoSevice;
	}
	
	private TodoService todoSevice;
	
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getUsername(model);
		model.addAttribute("todos", todoSevice.findByUsername(username));
		return "listTodos";
	}

	private static String getUsername(ModelMap model) {
		return (String) model.get("name");
	}

	@RequestMapping(value="add-todo",method= RequestMethod.GET)
	public String showNewTodoPage(ModelMap model){
		Todo todo =new Todo(0, getUsername(model),"",LocalDate.now().plusYears(1),false);
		model.put("todo",todo);
		return "todo";
	}

	@RequestMapping(value="add-todo",method= RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
		if(result.hasErrors()){
			return "todo";
		}
		todoSevice.addTodo(getUsername(model),todo.getDescription(), todo.getTargetDate(),false);

		return "redirect:list-todos";
	}

	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id){
		todoSevice.deleteById(id);

		return "redirect:list-todos";
	}

	@RequestMapping(value="update-todo",method= RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model){

		Todo todo = todoSevice.findById(id);
		model.addAttribute("todo",todo);
		return "todo";
	}

	@RequestMapping(value="update-todo",method= RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){
		if(result.hasErrors()){
			return "todo";
		}
		todo.setUsername(getUsername(model));
		todoSevice.updateTodo(todo);

		return "redirect:list-todos";
	}
}
