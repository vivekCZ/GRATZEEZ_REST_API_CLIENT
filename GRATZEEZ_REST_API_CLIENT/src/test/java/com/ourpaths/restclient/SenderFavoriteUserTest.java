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

public class SenderFavoriteUserTest extends BaseTest 
{
	private static final Log LOG = LogFactory.getLog(SenderFavoriteUserTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("Favorite.properties");
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
	 * This method is used for test add as service provider functionality.
	 * */
	
	@Test
	public void AddAsFavorite() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = addAsFavorite();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Add As Favorite Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation addAsFavorite() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("sender_id", prop.getProperty("sender_id"));
			jsonData.put("service_providerId", prop.getProperty("service_providerId"));
			jsonData.put("isFavorite", prop.getProperty("isFavorite"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_addAsFavorite");
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
	 * This method is used for test get favorite service provider functionality.
	 * */
	
	@Test
	public void GetFavoriteList() 
	{
		String responseData = null;
		Representation representation = null;

		try 
		{
			representation = favoriteList();
			System.out.println("Response Representation="+ representation);
			responseData = representation.getText();
			System.out.println("Add As Favorite Response=" + responseData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation favoriteList() 
	{
		Representation representation = null;
		JSONObject jsonData = null;
		try 
		{
			jsonData = new JSONObject();

			jsonData.put("sender_id", prop.getProperty("sender_id"));
			jsonData.put("request_for_favoriteList", prop.getProperty("request_for_favoriteList"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_favoriteList");
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
