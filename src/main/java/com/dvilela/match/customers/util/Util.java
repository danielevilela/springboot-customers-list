package com.dvilela.match.customers.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

public final class Util {
	
	
	public static List<String> getLines(String fileName) {
		List<String> lines = new ArrayList<String>();
		ClassLoader classLoader = Util.class.getClassLoader();
		try { 
		   lines =  Arrays.asList(IOUtils.toString(classLoader.getResourceAsStream(fileName)).split("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	 }
	
	public static double circleDistance(double lat, double lon) {		
		return distanceInKm(lat,lon);
	}
	
    public static double distanceInKm(double endLati, double endLong) {

        double diffLati = Math.toRadians(endLati - Constants.DUBLIN_LATITUDE);
        double diffLong = Math.toRadians(endLong - Constants.DUBLIN_LONGITUDE);
        
        double radiusStartLati = Math.toRadians(Constants.DUBLIN_LATITUDE);
        double radiusEndLati = Math.toRadians(endLati);

        double a = Math.pow(Math.sin(diffLati / 2), 2) + Math.pow(Math.sin(diffLong / 2), 2) * Math.cos(radiusStartLati) * Math.cos(radiusEndLati);
        double c = 2 * Math.asin(Math.sqrt(a));
        
        double result = Constants.EARTH_RADIUS * c;
        return Math.round(result * 100.0) / 100.0;
    }
}
