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

public class AddOrganizationTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(AddOrganizationTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("OrganizationInformation.properties");
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
	public void AddOrganization() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = organizationDetails();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Add Organization Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation organizationDetails() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			jsonData.put("userid", prop.getProperty("userid"));
			jsonData.put("organization_name", prop.getProperty("organization_name"));
			jsonData.put("address", prop.getProperty("address"));
			jsonData.put("landMark", prop.getProperty("landMark"));
			jsonData.put("city_id", prop.getProperty("city_id"));
			jsonData.put("state_id", prop.getProperty("state_id"));
			jsonData.put("contact_number", prop.getProperty("contact_number"));
			jsonData.put("website", prop.getProperty("website"));
			jsonData.put("zipcode", prop.getProperty("zipcode"));
			jsonData.put("latitude", prop.getProperty("latitude"));
			jsonData.put("longitude", prop.getProperty("longitude"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_addOrganization");
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

	/*This method is used for test to get all organization information functionality*/
	
	@Test
	public void OrganizationList() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = getOrganizationList();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Organization List Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation getOrganizationList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			
			jsonData.put("request_for_organizationList", prop.getProperty("request_for_organizationList"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_getOrganizationList");
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

	private Properties prop;
}
