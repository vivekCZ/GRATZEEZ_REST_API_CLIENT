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

public class FacebookLoginTest extends BaseTest {
	private static final Log LOG = LogFactory.getLog(FacebookLoginTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("FacebookLogin.properties");
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
	 * This method used to test login via facebook API
	 */
	@Test
	public void loginViaFacebook() throws Exception 
	{
		String response = "";
		Representation representation = null;

		try 
		{
			representation = facebookLogin();
			System.out.println("Response Representation=" + representation);
			response = representation.getText();
			System.out.println("Facebook Login Response=" + response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation facebookLogin() 
	{
		LOG.debug(FacebookLoginTest.class);
		Representation representation = null;
		try 
		{
			JSONObject jsonData = new JSONObject();

			jsonData.put("email", prop.getProperty("email"));
			jsonData.put("login_via", prop.getProperty("login_via"));
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_faceBookOperation");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} catch (ResourceException e) {
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
