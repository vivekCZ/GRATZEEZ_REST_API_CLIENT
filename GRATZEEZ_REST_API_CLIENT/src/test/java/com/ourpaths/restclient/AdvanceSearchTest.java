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

public class AdvanceSearchTest extends BaseTest {
	private static final Log LOG = LogFactory.getLog(AdvanceSearchTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("AdvanceSearch.properties");
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
	 * This method is used for test advance search API.
	 * */
	@Test
	public void advanceSearch() 
	{
		String responseData = null;
		Representation representation = null;
		try 
		{
			representation = search();
			System.out.println("Response Representation= " + representation);
			responseData = representation.getText();
			System.out.println("Advance Search Response:= " + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation search() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("user_name", prop.getProperty("user_name"));
			jsonData.put("e_name", prop.getProperty("e_name"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_advanceSearch");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} 
		catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could not Connect to REST API Server."+e.getMessage());
			return representation;

		} catch (Exception e) {
			e.printStackTrace();
			return representation;
		}

	}
	private Properties prop;
}
