package com.oday.RestAerospike.loader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oday.RestAerospike.Repository.CountryRepo;
import com.oday.RestAerospike.model.Country;
@Component


public class bootloader {
	
	CountryRepo conRepo;
	
	@Autowired
public bootloader(CountryRepo conRepo) {
	
		this.conRepo = conRepo;
	}



	@PostConstruct
	public void run()  {
	
		
		conRepo.deleteAll();
		Country conCountry=new Country();
		conCountry.setAge(12);
		conCountry.setName("Ahmad");
		conRepo.save(conCountry);
		
		
		conCountry.setAge(22);
		conCountry.setName("Oday");
		conRepo.save(conCountry);
		
		
		

	}



	

}
