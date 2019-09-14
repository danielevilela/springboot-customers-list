package com.dvilela.match.costumers;

import static org.junit.Assert.assertEquals;

import com.dvilela.match.customers.MatchCustomersApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.dvilela.match.customers.util.Constants;
import com.dvilela.match.customers.util.Util;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = 	MatchCustomersApplication.class
)
@AutoConfigureMockMvc
public class MatchCustomersApplicationTests {

	@Autowired
	MockMvc mockMVC;

	@Test
	public void contextLoads() throws Exception {
		MvcResult mvcResult = mockMVC.perform(
				MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)
		).andReturn();

		System.out.println(mvcResult.getResponse());
	}
	

}
