package com.zachdriggers.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {

	private static List<ToDo> todos = new ArrayList<ToDo>();
	private static int idCounter = 0;
	private static LocalDate dt = LocalDate.parse("2020-07-01");
	
	static 
	{
		todos.add(new ToDo(++idCounter, "validuser", "Learn to Program", dt, false));
		todos.add(new ToDo(++idCounter, "validuser", "Learn about Microservices", dt, false));
		todos.add(new ToDo(++idCounter, "validuser", "Learn about Angular", dt, false));
	}
	
	public List<ToDo> findAll(){
		 return todos;
	}
	
	public ToDo saveTodo(ToDo todo){
		if(todo.getId()==-1 || todo.getId()==0){
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else{
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public ToDo deleteById(long id)
	{
		ToDo todo = findById(id);
		
		if(todo == null) return null;
		
		if(todos.remove(todo))
			return todo;
		
		return null;
	}
	
	public ToDo findById(long id)
	{
		for(ToDo todo:todos)
		{
			if(todo.getId()== id)
			{
				return todo;
			}
		}
		return null;
	}
}
