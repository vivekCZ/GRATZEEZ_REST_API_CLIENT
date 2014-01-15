//This code is the property of Myriata Inc.
//Copyright Myriata Inc.

package com.ourpaths.restclient;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Client;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * This class contains integration test-cases for create new log entry and list
 * existing log entries.
 */
public class MembershipTest extends BaseTest {

        private static final Log LOG = LogFactory.getLog(MembershipTest.class);

        @Before
        public void setup() throws IOException {
                prop = loadPropertyFile("Membership.properties");
        }

        @After
        public void teardown() throws IOException {
                prop.clear();
                prop = null;
        }

        /**
         * This method calls REST API to check membership for given venue.
         */
        @Test
        public void isMember() {
                LOG.debug(MembershipTest.class+ ": isMember()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        
                        String userId = prop.getProperty("userid");
                        String venueId = prop.getProperty("venueid");
                        String forUserId = prop.getProperty("foruserid");
                        
                        uriParameters = uriParameters + "&userid="+userId+"&venueid="+venueId
                        							  + "&foruserid="+forUserId;
                        
                        String requestURL = prop.getProperty("api_uri_ismembership")+"/"+ uriParameters; 
                        System.out.println("requestURL:=>"+requestURL);
                        
                        Client client = getRestletClient();
                        ClientResource service = new ClientResource(requestURL);
                        
                        service.setNext(client);
                        Representation rep = service.get();
                        
                        String response = rep.getText();
                        System.out.println("rep.getText():=>"+response);
                        LOG.debug("---response=" + rep.getText());
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }

        /**
		 * This method calls REST API to check membership for given venue.
		 */
		@Test
		public void getUserVenues() {
		        LOG.debug(MembershipTest.class+ ": getUserVenues()");
		        try
		        {
		                String uriParameters = generateURIParameters(
		                                prop.getProperty("api-key"),
		                                prop.getProperty("api-secret"));
		                
		                String userId = prop.getProperty("userid");
		                
		                uriParameters = uriParameters +"&userid="+userId +"&venuegroupid="+prop.getProperty("venuegroupid");
		                
		                String requestURL = prop.getProperty("api_uri_uservenues")+"/"+ uriParameters;
//		                requestURL = "http://ec2-50-17-182-189.compute-1.amazonaws.com:8080/OURPATHS_REST_API/foundation-types/uservenues/?api-key=f56b8b3094678c609cd92a82&signature=24b0c325251de86bd9fce36483b47568&performer-token=ZIaQoVhwd9frl7ht5X6CNQ%3d%3d&userid=3";
		                System.out.println("requestURL:=>"+requestURL);
		                
		                Client client = getRestletClient();
		                ClientResource service = new ClientResource(requestURL);
		                
		                service.setNext(client);
		                Representation rep = service.get();
		                
		                String response = rep.getText();
		                System.out.println("getUserVenues response:=>"+response);
		                LOG.debug("---response=" + rep.getText());
		        } catch (Exception e)
		        {
		                LOG.error(e.toString(), e);e.printStackTrace();
		                Assert.fail();
		        }
		}

        
        private Properties prop;

}
