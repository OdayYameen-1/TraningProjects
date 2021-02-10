package com.javaworld.sample.HelloServiceImporting;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.javaworld.sample.service.HelloService;

public class Activator implements BundleActivator {
	
	 ServiceReference helloServiceReference;
	    public void start(BundleContext context) throws Exception {
	        System.out.println("Hello World!!");
	        helloServiceReference= context.getServiceReference(HelloService.class.getName());
	        HelloService helloService =(HelloService)context.getService(helloServiceReference);
	        System.out.println(helloService.sayHello());

	    }
	    public void stop(BundleContext context) throws Exception {
	        System.out.println("Goodbye World!!");
	        context.ungetService(helloServiceReference);
	    }
}
