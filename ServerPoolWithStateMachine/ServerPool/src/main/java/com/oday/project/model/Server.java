package com.oday.project.model;

import java.util.List;


import org.springframework.data.annotation.Id;

public class Server {
	@Id
	
	private Long id;
	
	
	
	private int capacity;
	
	
	private ServerStatus status=ServerStatus.Active;
	
	private int NoUser;
	
	private List <String> myUser;

	
//////////////////importante thing
	private long version;

	public Server(Long id, int capacity, ServerStatus status, int noUser, List<String> myUser,long version) {
		super();
		this.id = id;
		this.capacity = capacity;
		this.status = status;
		NoUser = noUser;
		this.myUser = myUser;
		this.version=version;
	}

	public Server() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ServerStatus getStatus() {
		return status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

	public int getNoUser() {
		return NoUser;
	}

	public void setNoUser(int noUser) {
		NoUser = noUser;
	}

	public List<String> getMyUser() {
		return myUser;
	}

	public void setMyUser(List<String> myUser) {
		this.myUser = myUser;
	}

	public String getStringUserName() {
		String x = "( ";
		for(String s:this.getMyUser()) {
			
			x+=" "+s+" , ";
			
		}
		x+=" )";
		return x;
		
		
	}
	
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Server ====>>>> [id=" + id + ", capacity=" + capacity + ", status=" + status + ", NoUser=" + NoUser + ", myUser="
				+ getStringUserName() + ", "+ " version  "+version+"]</br></br>";
	}

	
	
	
	
	
	
}
