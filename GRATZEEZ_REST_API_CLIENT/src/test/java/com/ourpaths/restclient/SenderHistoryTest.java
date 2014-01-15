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

public class SenderHistoryTest  extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(SenderHistoryTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("SenderHistory.properties");
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
	 * This method is used for test History API for Send Total Gratuity(For Sender).
	 * */
	
	@Test
	public void sendTotalGratuityHistory() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = totalGratuity();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Total Gratuity Send to Service Provider Response=" + responseData);
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
			
			jsonData.put("sender_id", prop.getProperty("sender_id"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_senderHistoryOfTotalGratuity");
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
	 * This method is used for test History API for Detail Send Gratuity(For Sender).
	 * */
	
	@Test
	public void detailSenderHistory() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = detailGratuity();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Detail Gratuity List Send by Sender Response=" + responseData);
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
			
			jsonData.put("sender_id", prop.getProperty("sender_id"));
			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_detailHistoryOfSenderGratuity");
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
