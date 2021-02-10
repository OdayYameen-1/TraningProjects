package com.javaworld.sample.Implem;

import com.javaworld.sample.service.HelloService;

public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello() {
		System.out.println("Inside hello service Implementation.sayHello() .....");
		return "Hi Hello";
	}

}
