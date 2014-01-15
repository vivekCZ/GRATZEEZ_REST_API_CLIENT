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

public class AddGratuityTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(AddOrganizationTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("AddGratuity.properties");
			System.out.println("This is try project");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@After
	public void teardown() throws IOException 
	{
		prop.clear();
		prop = null;
	}
	
	/**
	 * This method is used for test add new organization details functionality.
	 * */
	
	@Test
	public void AddGratuity() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = gratuityRatingComments();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Gratuity Rating And Comments Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation gratuityRatingComments() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			jsonData.put("sender_id", prop.getProperty("sender_id"));
			jsonData.put("rating", prop.getProperty("rating"));
			jsonData.put("gratuity", prop.getProperty("gratuity"));
			jsonData.put("comment", prop.getProperty("comment"));
						
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_addGratuity");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} 
		catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could not connect to REST API server. Make sure the server is running on given host address & port and REST API is deployed on it."
					+ e.getMessage());
			return representation;

		} catch (Exception e) 
		{
			e.printStackTrace();
			return representation;
		}

	}
	public Properties prop;
}
