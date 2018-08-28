package com.dvilela.match.customers.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class UtilTest {
	
		
	@Test
	public void readTxtToString() throws IOException {
		IOUtils.toString(
			      Util.class.getClassLoader().getResourceAsStream("customers.txt")
			    );
	}

	@Test
	public void testDublinCircleDistanceShouldReturnZero() {
		double result = Util.circleDistance(Constants.DUBLIN_LATITUDE,Constants.DUBLIN_LONGITUDE);
		assertTrue(result == 0);
	} 
 

}
