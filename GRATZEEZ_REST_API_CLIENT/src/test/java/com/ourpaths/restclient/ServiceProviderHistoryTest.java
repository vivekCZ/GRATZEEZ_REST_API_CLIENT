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

public class ServiceProviderHistoryTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(ServiceProviderHistoryTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("ServiceProviderHistory.properties");
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
	 * This method is used for test History API for receive Total Gratuity(For Service Provider).
	 * */
	
	@Test
	public void receiveTotalGratuityHistory() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = totalGratuity();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Total Gratuity receive from Sender Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation totalGratuity() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			
			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_serviceProviderHistoryOfTotalGratuity");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} 
		catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could Not Connect to REST API Server."+ e.getMessage());
			return representation;

		} catch (Exception e) 
		{
			e.printStackTrace();
			return representation;
		}
		
	}

	/**
	 * This method is used for test History API for Detail Receive Gratuity(For Service Provider).
	 * */
	
	@Test
	public void detailServiceProviderHistory() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = detailGratuity();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Detail Gratuity List Receive from Sender Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation detailGratuity() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();
			
			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			jsonData.put("sender_id", prop.getProperty("sender_id"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_detailHistoryOfServiceProviderGratuity");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} 
		catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could Not Connect to REST API Server."+ e.getMessage());
			return representation;

		} catch (Exception e) 
		{
			e.printStackTrace();
			return representation;
		}
		
	}
	private Properties prop;
}

