package com.dvilela.match.customers.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.dvilela.match.customers.service.CustomerService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.dvilela.match.customers.model.Customer;
import com.dvilela.match.customers.repository.CustomerRepository;
import com.dvilela.match.customers.util.Constants;
import com.dvilela.match.customers.util.Util;
import com.google.gson.Gson;

@Controller
public class CustomerController {
	
	private CustomerService customerService;

	private ModelAndView index = new ModelAndView("index");

	public CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}

	@GetMapping("/")
	public ModelAndView getAllMatches() {
		customerService.loadFile("customers.txt");
		List<Customer> customers = customerService.getCustomersByDistance(Constants.MAX_DISTANCE);
		index.addObject("title", "Matching customers within 100km");
		index.addObject("customers",customers);
		return index;
	}

	@GetMapping("/{distance}")
	public ModelAndView getAllMatchesByDistance(@PathVariable String distance) {
		customerService.loadFile("customers.txt");
		List<Customer> customers = customerService.getCustomersByDistance(Double.parseDouble(distance));
		index.addObject("title", "Matching customers within "+ distance + "KM");
		index.addObject("customers",customers);
		return index;
	}

	@GetMapping("/all")
	public ModelAndView getAllCustomers() {
		customerService.loadFile("customers.txt");
		List<Customer> customers = customerService.getAllCustomers();
		index.addObject("title", "List of customers");
		index.addObject("customers",customers);
		return index;
	}

	@GetMapping("/all/{id}")
	public ModelAndView getCustomerById(@PathVariable String id) {
		customerService.loadFile("customers.txt");
		List<Customer> customers = customerService.getCustomerById(id);
		index.addObject("title", "List of customers");
		index.addObject("customers",customers);
		return index;
	}
	  
}
