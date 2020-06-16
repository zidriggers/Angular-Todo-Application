package com.zachdriggers.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET, path= "/hello-world")
	//or
	@GetMapping(path="/hello-world")
	public String helloWorld(){
		return "Hello!";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		//throw new RuntimeException("Error Occured.");
		return new HelloWorldBean("Hello World - changed");
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
		return new HelloWorldBean(String.format("Hello, %s", name));
	}

}
