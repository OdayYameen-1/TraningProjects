package com.oday.project.repository;



import java.util.Optional;

import org.springframework.data.aerospike.repository.AerospikeRepository;


import com.oday.project.model.Server;

public interface ServerRepository extends AerospikeRepository<Server, Long>{

	default void update(Server s) throws IncorrectVersion {
		
		Server originServer=findById(s.getId()).get();
		
		
		System.out.println("id is = "+s.getId()+"   "+s.getVersion()+"      "+originServer.getVersion());
		if(s.getVersion()!=originServer.getVersion())
			throw new IncorrectVersion("incorrect version optimistic");
		else {
			System.err.println("id is = "+s.getId()+"   "+s.getVersion()+"      "+originServer.getVersion());
			s.setVersion(s.getVersion()+1);
			save(s);
		}
		
	};
	
	
}




	
	
	
	
	
	

