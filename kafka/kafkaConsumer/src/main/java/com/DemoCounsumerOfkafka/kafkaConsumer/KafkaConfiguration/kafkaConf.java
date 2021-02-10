package com.DemoCounsumerOfkafka.kafkaConsumer.KafkaConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import com.DemoCounsumerOfkafka.kafkaConsumer.Model.User;


@EnableKafka
@Configuration
public class kafkaConf {

	
	@Bean
	public ConsumerFactory<String, User> consumerFactory(){
		
		Map <String,Object> config =new HashMap<String, Object>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9093");
		config.put(ConsumerConfig.GROUP_ID_CONFIG,"mygroup_JSON");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<String, User>(config,new StringDeserializer(),new JsonDeserializer<>(User.class));
		
		
		
		
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,User> concurrentKafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String,User> factory=new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
		 factory.setErrorHandler(new KafkaErrHandler());
		return factory;
	}
}
