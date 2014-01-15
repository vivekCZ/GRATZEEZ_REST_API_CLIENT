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

import com.ourpaths.restclient.util.SecurityUtils;

public class AuthenticatorTest extends BaseTest {
	private static final Log LOG = LogFactory.getLog(AuthenticatorTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("Authentication.properties");
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
	 * This method used to test the Login API
	 */

	@Test
	public void nativeLogin() throws Exception {
		String response = "";
		Representation representation = null;

		try {

			representation = login();
			System.out.println("Response Representation=" + representation);
			response = representation.getText();
			System.out.println("Login Response=" + response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Representation login() 
	{
		LOG.debug(AuthenticatorTest.class + ": sendLoginRequest()");
		Representation representation = null;
		try 
		{
			JSONObject jsonData = new JSONObject();
			jsonData.put("user_name", prop.getProperty("user_name"));
			String password = SecurityUtils.getMD5(prop.getProperty("password"));
			jsonData.put("password", password);

			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_nativeLogin");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} 
		catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could Not Connect to REST API Server."+ e.getMessage());
			return representation;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			return representation;
		}
	}
	private Properties prop;
}
