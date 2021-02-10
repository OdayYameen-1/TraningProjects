package com.javaworld.sample.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.javaworld.sample.service.HelloService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		/*HelloService helloService=new HelloService() {
			
			@Override
			public String sayHello() {
			System.out.println("WELCOM WELCOM");
				return "the reterned welcom";
			}
		};
		
		String xString=helloService.sayHello();
		System.out.println(xString);*/
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good World");
		Activator.context = null;
	}

}
