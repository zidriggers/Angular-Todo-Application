package com.zachdriggers.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zachdriggers.rest.webservices.restfulwebservices.todo.ToDo;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoResource {

	@Autowired
	private TodoHardCodedService todoService;
	
	@GetMapping(path="/user/{userName}/todos")
	public List<ToDo> getAllTodos(@PathVariable String userName){
		return todoService.findAll();
	}
	
	@GetMapping(path="/user/{userName}/todos/{id}")
	public ToDo getTodo(@PathVariable String userName, @PathVariable long id){
		return todoService.findById(id);
	}
	
	@DeleteMapping(path="/user/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long id){
		ToDo todo = todoService.deleteById(id);
		if(todo != null)
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/user/{userName}/todos/{id}")
	public ResponseEntity<ToDo> updateTodo(@PathVariable String userName, 
			@PathVariable long id, 
			@RequestBody ToDo todo ){
		
		ToDo todoUpdated = todoService.saveTodo(todo);
		return new ResponseEntity<ToDo>(todoUpdated, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userName}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String userName, 
			@RequestBody ToDo todo ){
		
		ToDo createdTodo = todoService.saveTodo(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(createdTodo.getId())
		.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
