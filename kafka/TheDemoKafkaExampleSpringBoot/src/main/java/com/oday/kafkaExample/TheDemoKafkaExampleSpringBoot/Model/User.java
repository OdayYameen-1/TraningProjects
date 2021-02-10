package com.oday.kafkaExample.TheDemoKafkaExampleSpringBoot.Model;

public class User {

	
	private String name;
	private int age;
	private String dep;
	public User(String name, int age, String dep) {
		super();
		this.name = name;
		this.age = age;
		this.dep = dep;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	
	
	
	
}
