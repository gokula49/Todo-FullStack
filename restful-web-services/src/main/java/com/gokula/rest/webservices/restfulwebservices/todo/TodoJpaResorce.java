package com.gokula.rest.webservices.restfulwebservices.todo;

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
@CrossOrigin
@RestController
public class TodoJpaResorce {
	
	
	@Autowired
	private TodoHardcodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();
		
	}
	
	@GetMapping("/jpa/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
		
	}
	
	
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username , @PathVariable long id) {
		
     Todo todo = todoService.deleteById(id);
     
     todoJpaRepository.deleteById(id);
     if(todo!=null) {
    	 return ResponseEntity.noContent().build();
     }
     return ResponseEntity.notFound().build();
	}

	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username , 
			@PathVariable long id,
			@RequestBody Todo todo) {
		
		todo.setUsername(username);

	  Todo todoUpdated = todoJpaRepository.save(todo);
	  
	  return new ResponseEntity<Todo>(todo,HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username , 
			
			@RequestBody Todo todo) {
		
		todo.setUsername(username);

	  Todo createdTodo = todoJpaRepository.save(todo);
	  
	  URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			  .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
	  
	  return ResponseEntity.created(uri).build();
	}
}
