//This code is the property of Myriata Inc.
//Copyright Myriata Inc.

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

/**
 * This class contains integration test-cases for create Role, read Role and
 * delete Role
 */
public class RoleTest extends BaseTest
{

        private static final Log LOG = LogFactory.getLog(RoleTest.class);

        @Before
        public void setup() throws IOException
        {
                prop = loadPropertyFile("Role.properties");
        }

        @After
        public void teardown() throws IOException
        {
                prop.clear();
                prop = null;
        }

        /**
         * This method tests REST-API to read Role
         */
        @Test
        public void getAllRoles()
        {
                LOG.debug(RoleTest.class+": getAllRoles()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));

                        uriParameters = uriParameters + "&userid="+prop.getProperty("userid");
                        String requestURL = prop.getProperty("api_uri_read_role")+ "/"+uriParameters;
                        System.out.println("request url:=>"+requestURL);
                        
                        Client client = getRestletClient();
                        ClientResource service = new ClientResource(requestURL);
                        
                        service.setNext(client);
                        Representation rep = service.get();
                        
                        String response = rep.getText();
                        System.out.println("getAllRoles() response:=>"+response);
                        LOG.debug("---response=" + response);
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }
        
        /**
         * This method tests REST-API to read Role
         */
        @Test
        public void setUserVenueRoles()
        {
                LOG.debug(RoleTest.class+ ": setUserRoles()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));

                        JSONObject jsonData = generateJsonDataForRequest();
                        Client client = getRestletClient();
                        System.out.println("request URL:=>"+prop.getProperty("api_uri_set_user_venue_roles")+uriParameters);
                        System.out.println("post request param:=>"+jsonData.toString());
                        
                        ClientResource service = new ClientResource(prop.getProperty("api_uri_set_user_venue_roles")+uriParameters);
                        service.setNext(client);
                        Representation rep = service.post(jsonData.toString());
                        String response = rep.getText();
                        System.out.println("RoleTest: readRole() response:=>"+response);
                        LOG.debug("---response=" + response);
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }
        		
        /**
         * This method prepares required data into Json format for create new
         * log entry request.
         */
        private JSONObject generateJsonDataForRequest() {
                JSONObject jsonData = new JSONObject();
                jsonData.put("userid", prop.getProperty("userid"));
                jsonData.put("venueid", prop.getProperty("venueid"));
                jsonData.put("foruserid", prop.getProperty("foruserid"));
                jsonData.put("roleid1", prop.getProperty("roleid1"));
                jsonData.put("roleid2", prop.getProperty("roleid2"));
                jsonData.put("roleid3", prop.getProperty("roleid3"));
                return jsonData;
        }
        
        /**
         * This method tests REST-API to read Role
         */
        @Test
        public void getUserRoles()
        {
                LOG.debug(RoleTest.class+ ": getUserRoles()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        
                        uriParameters = uriParameters+"&userid="+prop.getProperty("userid")+"&venueid="+prop.getProperty("venueid")+"&foruserid="+prop.getProperty("foruserid");
                        Client client = getRestletClient();
                        String requestURL = prop.getProperty("api_uri_read_user_venue_role")+uriParameters;
                        System.out.println("from client requestURL:=>"+requestURL);
                        ClientResource service = new ClientResource(requestURL);
                        service.setNext(client);
                        Representation rep = service.get();
                        String response = rep.getText();
                        System.out.println("response:=>"+response);
                        LOG.debug("---response=" + response);
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }
        
        /**
         * This method tests REST-API to read users for particular venue
         */
        @Test
        public void getUsers()
        {
                LOG.debug(RoleTest.class+ ": getUsers()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        uriParameters = uriParameters + "&userid="+prop.getProperty("userid")+"&venueid="+prop.getProperty("venueid");
                        Client client = getRestletClient();
                        String requestURL = prop.getProperty("api_uri_read_users")+ uriParameters;
                        System.out.println("getUsers() requestURL:=>"+requestURL);
                        ClientResource service = new ClientResource(requestURL);
                        service.setNext(client);
                        Representation rep = service.get();
                        String response = rep.getText();
                        System.out.println("response:=>"+response);
                        LOG.debug("---response=" + response);
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }

        private Properties prop;
}
