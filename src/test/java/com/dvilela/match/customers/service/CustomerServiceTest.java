package com.dvilela.match.customers.service;

import com.dvilela.match.customers.model.Customer;
import com.dvilela.match.customers.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Autowired
    MockMvc mockMVC;

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void getAllCustomers() {

        List<Customer> listCustomer = new ArrayList<>();

        for(int i = 1; i < 11; i++){
            Customer c = new Customer();
            c.setUser_id((long)i);
            c.setName("Test "+i);
            listCustomer.add(c);
        }

        when(customerService.getAllCustomers()).thenReturn(listCustomer);

        List<Customer> result = customerService.getAllCustomers();

        assertThat(result,is(listCustomer));


    }

    @Test
    public void getCustomerById() {
        int randomId = ThreadLocalRandom.current().nextInt(1, 21);

        Customer c = new Customer();
        c.setUser_id((long)randomId);
        c.setName("Test User");

        when(customerService.getCustomerById(String.valueOf(randomId))).thenReturn(Arrays.asList(c));

        List<Customer> result =  customerService.getCustomerById(String.valueOf(randomId));

        assertThat(result.contains(c), is(true));
    }

    @Test
    public void getCustomersByDistance() {

        Customer c1 = new Customer();
        c1.setUser_id((long) 23);
        c1.setName("Save Customer Test");
        c1.setDistance(100);

        when(customerService.getCustomersByDistance(100)).thenReturn(Arrays.asList(c1));

        List<Customer> result = customerService.getCustomersByDistance(100);

        assertThat(result.contains(c1),is(true));
    }

    @Test
    public void saveCustomer(){

        Customer c1 = new Customer();
        c1.setUser_id((long) 23);
        c1.setName("Save Customer Test");

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(c1);

        Customer created = customerService.saveCustomer(c1);
        assertThat(created.getName(),is(c1.getName()));
    }
}