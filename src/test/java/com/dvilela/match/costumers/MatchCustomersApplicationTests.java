package com.dvilela.match.costumers;

import static org.junit.Assert.assertEquals;

import com.dvilela.match.customers.MatchCustomersApplication;
import com.dvilela.match.customers.model.Customer;
import com.dvilela.match.customers.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.dvilela.match.customers.util.Constants;
import com.dvilela.match.customers.util.Util;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = 	MatchCustomersApplication.class
)
@AutoConfigureMockMvc
public class MatchCustomersApplicationTests {

	@Autowired
	MockMvc mockMVC;

	@MockBean
	CustomerRepository customerRepository;

	public void setup(){

	}

	@Test
	public void contextLoads() throws Exception {
		MvcResult mvcResult = mockMVC.perform(
				MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)
		).andReturn();

		System.out.println(mvcResult.getResponse());
	}

	@Test
	public void customerById() throws Exception {
		Long id = new Random().nextLong();

		//List<Customer> result = customerRepository.findCustomerById((long)1);
		MvcResult mvcResult = mockMVC.perform(
				MockMvcRequestBuilders.get("/all/",1).accept(MediaType.APPLICATION_JSON)
		).andReturn();

		System.out.println(mvcResult.getResponse());
	}

	@Test
	public void testCustomerController () throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();

		ResultMatcher index = MockMvcResultMatchers.view().name("index");

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/all");
		this.mockMVC.perform(builder).andExpect(ok);

	}

}
