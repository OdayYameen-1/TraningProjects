package com.DemoCounsumerOfkafka.kafkaConsumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;

import com.DemoCounsumerOfkafka.kafkaConsumer.Model.User;

@Service
public class kafkaListener {

	
	@KafkaListener(topics = "Spring",groupId = "mygroup_JSON",containerFactory = "concurrentKafkaListenerContainerFactory")
	public void myConsumer(User u) {
		
		System.out.println("The message is recived ==> \n"+u.toString());
		
		
	}
	
}
