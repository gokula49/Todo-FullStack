package com.gokula.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HelloWorldController {
	
	
	@GetMapping(path="/helloworld")
	public String helloWorld() {
	 return "Hello World";	
	}
	
	@GetMapping(path="/helloworldbean")
	public HelloWorldBean helloWorldBean() {
	 return new HelloWorldBean("Hello World changed");	
	}
	@GetMapping(path="/helloworld/pathvariable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
	 return new HelloWorldBean(String.format("Hello World, %s", name));	
	}
	
}
