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

public class DisplayServiceProviderInformationTest extends BaseTest {
	private static final Log LOG = LogFactory.getLog(DisplayServiceProviderInformationTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("DisplayServiceProviderInformation.properties");
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
	 * This method is used for test Display Service Provider Home Page Information API.
	 * */
	
	@Test
	public void displayServiceProviderInformation() 
	{
		String responseData = null;
		Representation representation = null;
		try 
		{
			representation = gratuityTab();
			System.out.println("Response Representation= " + representation);
			responseData = representation.getText();
			System.out.println("Service Provider Home Page Response:= " + responseData);
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

			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
						
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_serviceProviderHomePage");
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
	
	/**
	 * This method is used for test Display Servicer Provider Rate List API.
	 * */
	@Test
	public void getRateList() 
	{
		String responseData = null;
		Representation representation = null;
		try 
		{
			representation = rateList();
			System.out.println("Response Representation= " + representation);
			responseData = representation.getText();
			System.out.println("Rate List Response:= " + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation rateList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
						
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_rateList");
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

	/**
	 * This method is used for test Display Commented User List API.
	 * */
	@Test
	public void getCommentedUserList() 
	{
		String responseData = null;
		Representation representation = null;
		try 
		{
			representation = userList();
			System.out.println("Response Representation= " + representation);
			responseData = representation.getText();
			System.out.println("Commented User List Response:= " + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation userList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
						
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_commentedUserList");
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
	
	/**
	 * This method is used for test Display details Comment List API.
	 * */
	@Test
	public void detailCommentList() 
	{
		String responseData = null;
		Representation representation = null;
		try 
		{
			representation = comments();
			System.out.println("Response Representation= " + representation);
			responseData = representation.getText();
			System.out.println("Comment List Response:= " + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation comments() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			jsonData.put("sender_id", prop.getProperty("sender_id"));
						
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_detailCommentList");
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
