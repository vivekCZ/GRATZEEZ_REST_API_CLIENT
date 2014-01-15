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

public class InviteNewUserTest extends BaseTest
{
	private static final Log LOG = LogFactory.getLog(InviteNewUserTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("InviteNewUser.properties");
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
	 * This method is used for test invite new user functionality.
	 * */
	@Test
	public void inviteNewUser() 
	{
		String responseData = null;
		Representation representation = null;

		try {
			representation = invite();
			System.out.println("Invite User Response Representation:= "
					+ representation);
			responseData = representation.getText();
			System.out.println("Invite User Response:= " + responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation invite() {
		Representation representation = null;
		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject();
			
			jsonData.put("email", prop.getProperty("email"));
			jsonData.put("sender_id",prop.getProperty("sender_id"));
			jsonData.put("message", prop.getProperty("message"));
			jsonData.put("gratuity", prop.getProperty("gratuity"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_inviteNewUser");
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
