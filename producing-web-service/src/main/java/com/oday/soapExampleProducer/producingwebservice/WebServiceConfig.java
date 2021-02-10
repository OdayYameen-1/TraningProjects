package com.oday.soapExampleProducer.producingwebservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan(basePackages = "com.oday.movies")
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	@Bean(name = "countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CountriesPort");
		wsdl11Definition.setLocationUri("/ws/countries");
		wsdl11Definition.setTargetNamespace("http://ahmad.org/guides/oday-producing-the-web-service");
		wsdl11Definition.setSchema(countriesSchema);
		return wsdl11Definition;
	}
	@Bean(name = "movies")
	public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema moviesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MoviesPort");
		wsdl11Definition.setLocationUri("/ws/movies");
		wsdl11Definition.setTargetNamespace("http://pheely.io/get-movie-web-service");
		wsdl11Definition.setSchema(moviesSchema);
		return wsdl11Definition;
	}
	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
		
	}
	@Bean
	public XsdSchema moviesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("movies.xsd"));
	}

}


