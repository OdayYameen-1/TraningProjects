package com.oday.kafkaExample.TheDemoKafkaExampleSpringBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oday.kafkaExample.TheDemoKafkaExampleSpringBoot.Model.User;

@RestController

public class UserController {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	private String theTopic="Spring";
	
	@GetMapping("/publish/{id}/{name}")
	public String publish(@PathVariable("id")int id,@PathVariable("name")String name) {
	User user=new User(id,name);
	kafkaTemplate.send(theTopic,user);
		
	return "the user published sucssessfully";	
		
	}
	
	
	
}
