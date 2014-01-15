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

public class CharityInformationTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(CharityInformationTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("CharityInformation.properties");
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
	 * This method is used for test functionality of add new charity and send mail to the charity.
	 * */
	
	@Test
	public void InviteCharity() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = addCharity();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Invite Charity Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation addCharity() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			jsonData.put("charity_id", prop.getProperty("charity_id"));
			jsonData.put("charity_name", prop.getProperty("charity_name"));
			jsonData.put("charity_mail", prop.getProperty("charity_mail"));
			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			jsonData.put("gratuity_percentage", prop.getProperty("gratuity_percentage"));

			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_inviteCharity");
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

	/**
	 * This method is used for test functionality of get charity List.
	 * */
	
	@Test
	public void GetCharity() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = charityList();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Invite Charity Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation charityList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
		
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_getCharityList");
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
