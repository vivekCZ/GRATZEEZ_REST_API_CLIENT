package com.ourpaths.restclient;
import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Client;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class GPSSearchTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(GPSSearchTest.class);

	@Before
	public void setup() throws IOException 
	{
		try {
			prop = loadPropertyFile("GPSSearch.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void teardown() throws IOException {
		prop.clear();
		prop = null;
	}
	
	/**
	 * This method is used to test the GPS Search-near by Location
	 * */

	@Test
	public void nearByLocationSearch() 
	{
		String responseData = null;
		Representation representation = null;

		try {
			representation = gpsSearch();
			System.out.println("Response Representation=" + representation);
			responseData = representation.getText();
			System.out.println("GPS Search Response=" + responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation gpsSearch() {
		Representation representation = null;
		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject();

			jsonData.put("latitude", prop.getProperty("latitude"));
			jsonData.put("longitude", prop.getProperty("longitude"));

			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_nearByLocationSearch");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} catch (ResourceException e) {
			e.printStackTrace();
			LOG.error("Could not Connect to REST API Server."+ e.getMessage());
			return representation;

		} catch (Exception e) {
			e.printStackTrace();
			return representation;
		}

	}
	
	
	/**
	 * This method is used for test get Landmark List.
	 * */
	
	@Test
	public void landmarkList() {
		String responseData = null;
		Representation representation = null;

		try {
			representation = landmarkNameList();
			System.out.println("Response Representation=" + representation);
			responseData = representation.getText();
			System.out.println("Landmark Response:= " + responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation landmarkNameList() {
		Representation representation = null;
		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject();

			jsonData.put("landmarkName", prop.getProperty("landmarkName"));
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_searchBylandmarkName");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} catch (ResourceException e) {
			e.printStackTrace();
			LOG.error("Could not Connect to REST API Server."+ e.getMessage());
			return representation;

		} catch (Exception e) {
			e.printStackTrace();
			return representation;
		}

	}


	/**
	 * This method is used for test GPS Search - Search by landmark name.
	 * */
	@Test
	public void searchBylandmarkName() {
		String responseData = null;
		Representation representation = null;

		try {
			representation = landmarkSearch();
			System.out.println("Response Representation=" + representation);
			responseData = representation.getText();
			System.out.println("GPS-Search by Landmark Name Response:= " + responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation landmarkSearch() {
		Representation representation = null;
		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject();

			jsonData.put("landmarkName", prop.getProperty("landmarkName"));
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_searchBylandmarkName");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} catch (ResourceException e) {
			e.printStackTrace();
			LOG.error("Could not Connect to REST API Server."+ e.getMessage());
			return representation;

		} catch (Exception e) {
			e.printStackTrace();
			return representation;
		}

	}

	private Properties prop;
}
