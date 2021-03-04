package com.DemoCounsumerOfkafka.kafkaConsumer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;

import com.DemoCounsumerOfkafka.kafkaConsumer.KafkaConfiguration.kafkaConf;
import com.DemoCounsumerOfkafka.kafkaConsumer.Model.User;
import com.oday.AerospikeConfiguration.AerospikeConfiguration;
import com.oday.AerospikeConfiguration.UserRepository;

@Service
@ComponentScan(basePackageClasses = AerospikeConfiguration.class)
@ComponentScan(basePackageClasses = kafkaConf.class)
public class kafkaListener {
		
		
	@Autowired
	UserRepository userRepository;
	
	@KafkaListener(topics = "Spring",groupId = "mygroup_JSON",containerFactory = "kafkaListenerContainerFactory")
	public void myConsumer(User u) {
		
		System.out.println("The message is recived ==> \n"+u);
		
		
	}
	
}
