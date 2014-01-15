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

public class ChangePasswordTest extends BaseTest
{
	private static final Log LOG = LogFactory.getLog(ChangePasswordTest.class);

	@Before
	public void setup() throws IOException 
	{
		try 
		{
			prop = loadPropertyFile("ChangePassword.properties");
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
	 * This method used to test the change password API
	 */

	@Test
	public void changePassword() throws Exception 
	{
		String response = "";
		Representation representation = null;

		try 
		{
			representation = passwordUpdate();
			System.out.println("Response Representation=" + representation);
			response = representation.getText();
			System.out.println("Change Password Response=" + response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation passwordUpdate() 
	{
		LOG.debug(AuthenticatorTest.class);
		Representation representation = null;
		try 
		{
			JSONObject jsonData = new JSONObject();
			jsonData.put("userid", prop.getProperty("userid"));
			String password = SecurityUtils.getMD5(prop.getProperty("current_password"));
			jsonData.put("current_password", password);
			String newpassword = SecurityUtils.getMD5(prop.getProperty("new_password"));
			jsonData.put("new_password", newpassword);

			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_changePassword");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} catch (ResourceException e) 
		{
			e.printStackTrace();
			LOG.error("Could Not Connect to REST API Server." + e.getMessage());
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
