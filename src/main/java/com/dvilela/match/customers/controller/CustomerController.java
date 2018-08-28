package com.dvilela.match.customers.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dvilela.match.customers.model.Customer;
import com.dvilela.match.customers.repository.CustomerRepository;
import com.dvilela.match.customers.util.Constants;
import com.dvilela.match.customers.util.Util;
import com.google.gson.Gson;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository iCustomer;

	@GetMapping("/customers")
	public ModelAndView getMatches() {
		ModelAndView modelAndViewMatches = new ModelAndView("index");
		loadFile("customers.txt");
		List<Customer> customers = iCustomer.filterCustomersByDistance(Constants.MAX_DISTANCE);
		modelAndViewMatches.addObject("customers",customers);		
		return modelAndViewMatches;
	}
	public void loadFile(String fileName) {
		Gson gson = new Gson();
		Util.getLines(fileName).forEach(line -> {			
			Customer customer = gson.fromJson(line, Customer.class);
			customer.setDistance(Util.circleDistance(customer.getLatitude(), customer.getLongitude()));
			System.out.println(line);
			saveCustomer(customer);
		});
	}
	
	public void saveCustomer(Customer customer) {
		this.iCustomer.save(customer);		
	}
	  
}
