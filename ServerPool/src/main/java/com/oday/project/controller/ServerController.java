package com.oday.project.controller;



import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.oday.project.configuration.AerospikeConfiguration;
import com.oday.project.model.Server;
import com.oday.project.model.ServerStatus;
import com.oday.project.repository.IncorrectVersion;
import com.oday.project.repository.ServerRepository;

@RestController
@ComponentScan(basePackageClasses = AerospikeConfiguration.class)
public class ServerController {
	@Autowired
	private ServerRepository serverRepository;
	
	private Thread t;
	
	@RequestMapping(value = "/getServer",headers = "Accept=application/json")
	public Iterable<Server> getServer() {
		
		return serverRepository.findAll();

	}

	@RequestMapping(value = "/getMyServer/{id}",headers = "Accept=application/json")
	public Server getMyServer(@PathVariable(value  = "id") long id) {
		
		return serverRepository.findById(id).get();

	}

	@RequestMapping(value = "/allocate/{capacity}/{nameOfUser}",headers = "Accept=application/json")
	public String allocate(@PathVariable(value = "capacity") int capacity,
			@PathVariable(value = "nameOfUser") String nameOfUser) {
		if (capacity > 100)
			return "Your capacity grater than 100";
		
		String string= allocateServer( capacity,nameOfUser);
		return string;
		
		
	}
	private String allocateServer(int capacity, String nameOfUser) {
		List<Server> servers = new ArrayList<Server>();
		serverRepository.findAll().forEach(servers::add);

		Server s = getBestServer(servers, capacity);

		s.setStatus(ServerStatus.Createing);
		 System.out.println("the server with id= " +s.getId()+" now is in = "+s.getStatus());

		s.getMyUser().add(nameOfUser);

		if (s.getNoUser() == 0) {
			s.setNoUser(s.getNoUser() + 1);
			s.setCapacity(capacity);

		} else {
			s.setCapacity(s.getCapacity() + capacity);
			s.setNoUser(s.getNoUser() + 1);
		}
		
		
		try {
			serverRepository.update(s);
		} catch (IncorrectVersion e) {
			System.err.println("eeeeeeeeerrrrrrrrrrrrrroooooooooooorrrrrrr");
			return allocateServer(capacity, nameOfUser);
			
		}
	
			Thread thread=new Thread(new Runnable() {
				
				@Override
				public void run() {
				try {
					Thread.sleep(12000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				s.setStatus(ServerStatus.Active);
				 System.out.println("the server with id= " +s.getId()+" now is in = "+s.getStatus());
				serverRepository.save(s);
					
				}
			});
					
			thread.start();
		return "you will take the server after 12 sec , your server id ="+s.getId();
		
	}


///////////////////////*///////////////////////////////*///////////////////////
	private Server getBestServer(List<Server> servers, int capacity) {

		for (Server server : servers) {
			if (server.getNoUser() == 0)
				return server;
		}
		List<Server> tofindMin = new ArrayList<Server>();
		for (Server server : servers) {

			if (server.getCapacity() + capacity <= 100) {
				tofindMin.add(server);

			}

		}
		Optional<Server> theminServerCapacity = tofindMin.stream().min(new Comparator<Server>() {

			@Override
			public int compare(Server o1, Server o2) {
				if (o1.getCapacity() > o2.getCapacity())

					return 1;

				else if (o1.getCapacity() < o2.getCapacity())
					return -1;

				else

					return 0;
			}

		});////// end of find min server capacity

		if (!theminServerCapacity.isPresent()) {

			long newid = (System.currentTimeMillis() << 20) | (System.nanoTime() & 0xFFFFFL);
			List<String> l = new ArrayList<String>();
			l.add("");
			Server nnsServer = new Server(newid, 0, ServerStatus.Active, 0, l, 1);
			serverRepository.save(nnsServer);
			return nnsServer;
		}

		return theminServerCapacity.get();

	}

}
