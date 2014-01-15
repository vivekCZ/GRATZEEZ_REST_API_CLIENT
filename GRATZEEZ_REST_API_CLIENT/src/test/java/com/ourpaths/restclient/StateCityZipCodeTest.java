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

public class StateCityZipCodeTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(AddOrganizationTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("StateCityZipCode.properties");
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
	 * This method is used for test get the state list.
	 * */
	
	@Test
	public void GetState() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = stateList();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("State List Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation stateList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_getState");
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

	/*This method is used for test to get city list for the requested state id*/
	
	@Test
	public void GetCityList() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = cityList();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("City List Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation cityList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			
			jsonData.put("state_id", prop.getProperty("state_id"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_getCity");
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
	
	/*This method is used for test to get zipcode list for the requested city id*/
	
	@Test
	public void GetZipcode() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = zipcodeList();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Zipcode List Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation zipcodeList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			
			jsonData.put("city_id", prop.getProperty("city_id"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_getZipcode");
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
