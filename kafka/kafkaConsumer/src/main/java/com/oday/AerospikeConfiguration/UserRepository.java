package com.oday.AerospikeConfiguration;

import org.springframework.data.aerospike.repository.AerospikeRepository;

import com.DemoCounsumerOfkafka.kafkaConsumer.Model.User;

public interface UserRepository extends AerospikeRepository<User, Integer>{

}
