package com.ourpaths.restclient;

import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Client;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.ourpaths.restclient.util.SecurityUtils;

public class RegistrationTest extends BaseTest {
	private static final Log LOG = LogFactory
			.getLog(RegistrationTest.class);

	@Before
	public void setup() throws IOException {
		prop = loadPropertyFile("User.properties");
	}

	@After
	public void teardown() throws IOException {
		prop.clear();
		prop = null;
	}

	/**
	 * This method used to test the Registration API
	 */
	@Test
	public void createUser() {
		LOG.debug(RegistrationTest.class + ": getUserList()");
		try {
			JSONObject jsonData = new JSONObject();

			jsonData.put("first_name", prop.getProperty("first_name"));
			jsonData.put("last_name", prop.getProperty("last_name"));
			jsonData.put("user_name", prop.getProperty("user_name"));
			String password = SecurityUtils.getMD5(prop.getProperty("password"));
			jsonData.put("password", password);
			jsonData.put("email", prop.getProperty("email"));
			jsonData.put("contact_number", prop.getProperty("contact_number"));
			jsonData.put("gender", prop.getProperty("gender"));
			jsonData.put("service_provider",prop.getProperty("service_provider"));

			Client client = getRestletClient();
			String requesturl = prop.getProperty("api_uri_registration");

			ClientResource service = new ClientResource(requesturl);
			service.setNext(client);
			Representation rep = service.post(jsonData.toString());
			String response = rep.getText();
			System.out.println("createUser Response=" + response);
			LOG.debug("Response=" + response);
		}
		catch (Exception e) 
		{
			LOG.error(e.toString(), e);
			e.printStackTrace();
			Assert.fail();
		}
	}

	private Properties prop;
}
