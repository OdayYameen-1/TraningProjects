package com.oday.soapExampleProducer.producingwebservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.ahmad.guides.oday_producing_the_web_service.Country;
import org.ahmad.guides.oday_producing_the_web_service.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.KeyRecord;
import com.oday.aerospike.JSONRecord;

import Configration.AerospikeConfiguration;



@Component
@Repository
public class CountryRepository implements CommandLineRunner{
	

	@Autowired
	AerospikeClient client;
	


	public Country findCountry(String name) {
		Policy policy = new Policy();
		Key key = new Key("test", "Countries", name);
		Record record = client.get(policy, key);
		
	Map<String, Object> thebinsMap=	record.bins;
	Country country=new Country();
	country.setName((String)thebinsMap.get("Name"));
	country.setCapital((String)thebinsMap.get("Capital"));
	country.setCurrency((Currency)thebinsMap.get("Currency"));
	country.setPopulation((Long)thebinsMap.get("Population"));
	
		return country;
	}

	@Override
	public void run(String... args) throws Exception {
WritePolicy wp = new WritePolicy();
		
		
		
		
		Bin setName=new Bin("Name","Spain");
		Bin setCapital=new Bin("Capital","Madrid");
		Bin setCurrency=new Bin("Currency",Currency.EUR);
		Bin setPopulation=new Bin("Population",46704314);
		Key spainKey=new Key("test", "Countries", "Spain"); 
		client.put(wp, spainKey, setName,setCapital,setCurrency,setPopulation);
		
		
		
		
		
		Bin setName1=new Bin("Name","Poland");
		Bin setCapital1=new Bin("Capital","Warsaw");
		Bin setCurrency1=new Bin("Currency",Currency.PLN);
		Bin setPopulation1=new Bin("Population",38186860);
		Key PolanKey=new Key("test", "Countries", "Poland"); 
		client.put(wp, PolanKey, setName1,setCapital1,setCurrency1,setPopulation1);
		
		

		
		Bin setName2=new Bin("Name","UK");
		Bin setCapital2=new Bin("Capital","London");
		Bin setCurrency2=new Bin("Currency",Currency.GBP);
		Bin setPopulation2=new Bin("Population",63705000);
		Key ukKey=new Key("test", "Countries", "UK"); 
		client.put(wp, ukKey, setName2,setCapital2,setCurrency2,setPopulation2);
		
		
	}
}
