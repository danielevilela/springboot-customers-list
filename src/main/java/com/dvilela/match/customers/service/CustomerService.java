package com.dvilela.match.customers.service;

import com.dvilela.match.customers.model.Customer;
import com.dvilela.match.customers.repository.CustomerRepository;
import com.dvilela.match.customers.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerById(String id){
        return customerRepository.findCustomerById(Long.parseLong(id));
    }

    public List<Customer> getCustomersByDistance(double distance){
        return customerRepository.filterCustomersByDistance(distance);
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

    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }
}
