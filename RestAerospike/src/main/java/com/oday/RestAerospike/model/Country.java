package com.oday.RestAerospike.model;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;

public class Country {

@Id
private String name;

private int Age;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return Age;
}

public void setAge(int age) {
	Age = age;
}

@Override
public String toString() {
	return "Country [name=" + name + ", Age=" + Age + "]";
}




}
