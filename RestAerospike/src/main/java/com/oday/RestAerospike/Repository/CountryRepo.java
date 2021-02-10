package com.oday.RestAerospike.Repository;

import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.oday.AerospikeConfiguration.AerospikeConfiguration;
import com.oday.RestAerospike.model.Country;




public interface CountryRepo extends AerospikeRepository<Country, String>{
	
}
