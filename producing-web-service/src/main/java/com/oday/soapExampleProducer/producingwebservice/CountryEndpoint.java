package com.oday.soapExampleProducer.producingwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import Configration.AerospikeConfiguration;

import org.ahmad.guides.oday_producing_the_web_service.GetCountryRequest;
import org.ahmad.guides.oday_producing_the_web_service.GetCountryResponse;
@Endpoint
@ComponentScan(basePackageClasses  = AerospikeConfiguration.class)
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://ahmad.org/guides/oday-producing-the-web-service";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
}
