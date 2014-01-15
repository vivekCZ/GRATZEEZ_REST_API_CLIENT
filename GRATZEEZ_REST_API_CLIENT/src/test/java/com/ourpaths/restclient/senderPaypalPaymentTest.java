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

public class senderPaypalPaymentTest extends BaseTest {
	private static final Log LOG = LogFactory
			.getLog(senderPaypalPaymentTest.class);

	@Before
	public void setup() throws IOException {
		try {
			prop = loadPropertyFile("SenderPayment.properties");
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
	 * This method is used for test advance search functionality.
	 * */
	@Test
	public void senderPayment() {
		String responseData = null;
		Representation representation = null;

		try {
			representation = sender();
			System.out.println("Advance Search Response Representation:= "
					+ representation);
			responseData = representation.getText();
			System.out.println("Advance Search Response:= " + responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Representation sender() {
		Representation representation = null;
		JSONObject jsonData = null;
		try {
			jsonData = new JSONObject();

			jsonData.put("userid", prop.getProperty("user_id"));
			jsonData.put("amount", prop.getProperty("amount"));
			jsonData.put("app_id", prop.getProperty("app_id"));
			jsonData.put("currency_code", prop.getProperty("currency_code"));
			jsonData.put("pay_key", prop.getProperty("pay_key"));
			jsonData.put("payment_exec_status", prop.getProperty("payment_exec_status"));
			jsonData.put("timestamp", prop.getProperty("timestamp"));
			
			Client client = getRestletClient();
			String url = prop.getProperty("api_uri_senderPaypalPayment");
			ClientResource service = new ClientResource(url);
			service.setNext(client);
			return service.post(jsonData.toString());
		} catch (ResourceException e) {
			e.printStackTrace();
			LOG.error("Could not connect to REST API server. Make sure the server is running on given host address & port and REST API is deployed on it."
					+ e.getMessage());
			return representation;

		} catch (Exception e) {
			e.printStackTrace();
			return representation;
		}

	}

	private Properties prop;
}
