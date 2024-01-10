package com.manoj.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 3;
	static {
		todos.add(new Todo(1,"Manoj","Learn AWS",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(2,"Manoj","Learn Devops",LocalDate.now().plusYears(2),false));
		todos.add(new Todo(3,"Manoj","Learn Full Stack Development",LocalDate.now().plusYears(3),false));
	}
	
	public List<Todo> findByUsername(String username){

		Predicate<? super Todo> predicate= todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}

	public void addTodo(String username,String description,LocalDate targetDate,boolean isDone){
		Todo todo = new Todo(++todosCount,username,description,targetDate,isDone);
		todos.add(todo);
	}

	public void deleteById(int id){
		Predicate<? super Todo> predicate= todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}


	public Todo findById(int id) {
		Predicate<? super Todo> predicate= todo -> todo.getId()==id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
