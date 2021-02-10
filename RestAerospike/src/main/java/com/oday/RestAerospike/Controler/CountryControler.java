package com.oday.RestAerospike.Controler;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oday.AerospikeConfiguration.AerospikeConfiguration;
import com.oday.RestAerospike.Repository.CountryRepo;
import com.oday.RestAerospike.model.Country;

@Controller

@ComponentScan(basePackages = "com.oday.AerospikeConfiguration")
public class CountryControler {

private CountryRepo countryRepo;


@Autowired
public CountryControler(CountryRepo countryRepo) {
	super();
	this.countryRepo = countryRepo;
}



@GetMapping("/{name}")
@ResponseBody
public String getCountry(@PathVariable String name) {
	
	
	  Optional<Country> counOptional= countryRepo.findById(name);
	if(!counOptional.isPresent()) {
		return"no country by this name";
	}
	else return counOptional.toString();	
	
	
}
	
	
	
}
