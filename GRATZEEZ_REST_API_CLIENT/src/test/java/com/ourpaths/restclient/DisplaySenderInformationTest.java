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

public class DisplaySenderInformationTest extends BaseTest {
	private static final Log LOG = LogFactory.getLog(DisplaySenderInformationTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("DisplaySenderInformation.properties");
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
	 * This method is used for test Display Sender Home Page Information API.
	 * */
	@Test
	public void displaySenderInformation() 
	{
		String responseData = null;
		Representation representation = null;
		try 
		{
			representation = gratuityTab();
			System.out.println("Response Representation= " + representation);
			responseData = representation.getText();
			System.out.println("Sender Home Page Response:= " + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation gratuityTab() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("sender_id", prop.getProperty("sender_id"));
						
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_senderHomePage");
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
