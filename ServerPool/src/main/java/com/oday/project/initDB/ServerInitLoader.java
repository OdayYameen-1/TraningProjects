package com.oday.project.initDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.oday.project.configuration.AerospikeConfiguration;
import com.oday.project.model.Server;
import com.oday.project.model.ServerStatus;
import com.oday.project.repository.ServerRepository;

@Component
@ComponentScan(basePackageClasses = AerospikeConfiguration.class)
public class ServerInitLoader implements CommandLineRunner{
	@Autowired
	private ServerRepository serverRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		//delete all thing in db
		serverRepository.deleteAll();
		
		//craete defualt server
		List<String> list=new ArrayList<String>();
		list.add("");
		Server s1=new Server((long) 1,30,ServerStatus.Active,0,list,1);
		/*Server s2=new Server((long) 2,50,ServerStatus.Active,0,list,1);
		Server s3=new Server((long) 3,20,ServerStatus.Active,0,list,1);
		Server s4=new Server((long) 4,20,ServerStatus.Active,0,list,1);
		*/
		
		//Save Server
		
		serverRepository.saveAll(Arrays.asList(s1));
		
		
	}

}
