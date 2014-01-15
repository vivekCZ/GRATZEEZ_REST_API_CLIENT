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

public class ForgotPasswordTest extends BaseTest {
	private static final Log LOG = LogFactory.getLog(AuthenticatorTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("ForgotPassword.properties");
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
	 * This method used to test forgot password API
	 */

	@Test
	public void forgotPassword() throws Exception 
	{
		String response = "";
		Representation representation = null;

		try 
		{
			representation = sendPassword();
			System.out.println("Response Representation=" + representation);
			response = representation.getText();
			System.out.println("Forgot Password Response:=> " + response);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private Representation sendPassword() 
	{
		LOG.debug(AuthenticatorTest.class);
		Representation representation = null;
	
		try 
		{
			JSONObject jsonData = new JSONObject();

			jsonData.put("user_name", prop.getProperty("user_name"));
			jsonData.put("email", prop.getProperty("email"));

			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_forgotPassword");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} 
		catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could Not Connect to REST API Server."+ e.getMessage());
			return representation;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			return representation;
		}
	}

	private Properties prop;
}
