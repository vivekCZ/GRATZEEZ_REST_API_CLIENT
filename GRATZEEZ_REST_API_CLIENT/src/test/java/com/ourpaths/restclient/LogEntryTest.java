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
 * This class contains integration test-cases for create new log entry and list
 * existing log entries.
 */
public class LogEntryTest extends BaseTest {

        private static final Log LOG = LogFactory.getLog(LogEntryTest.class);

        @Before
        public void setup() throws IOException {
                prop = loadPropertyFile("Logging.properties");
        }

        @After
        public void teardown() throws IOException {
                prop.clear();
                prop = null;
        }

        /**
         * This method calls REST API to create new log entry.
         */
        @Test
        public void setNewLogEntry() {
                LOG.debug("createNewLogEntry()");
                try
                {
                		setup();
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));

                        JSONObject jsonData = generateJsonDataForRequest();

                        Client client = getRestletClient();
                        String str = prop.getProperty("api_uri_request_create_new_log_entry") + uriParameters;
                        ClientResource service = new ClientResource(str);
                        service.setNext(client);
                        Representation rep = service.post(jsonData.toString());
                        System.out.println("Client rep.getText():=>"+rep.getText());
                        LOG.debug("---response=" + rep.getText());
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }

        /**
         * This method calls REST API to retrieve list of existing log-entries
         * based on provided criteria.
         */
        @Test
        public void listLogEntry() {
                LOG.debug(LogEntryTest.class+ ": listLogEntry()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        
                        String userId = prop.getProperty("userid");
                        String entityId = prop.getProperty("entityid");
                        String entityType = prop.getProperty("entitytype");
                        String startDate = prop.getProperty("startdate");
                        String endDate = prop.getProperty("enddate");
                        String click = prop.getProperty("click");
                        
                        uriParameters = uriParameters + "&userid="+ userId+ "&entityid="+ entityId
                        							  + "&entitytype="+entityType+ "&startdate="+ startDate
                        							  + "&enddate="+ endDate+ "&click="+ click;
                        
                        String requestURL = prop.getProperty("api_uri_request_list_log_entries") + uriParameters; 
                        System.out.println("requestURL:=>"+requestURL);
                        ClientResource service = new ClientResource(requestURL);
                        Client client = getRestletClient();
                        service.setNext(client);
                        Representation rep = service.get();
                        
                        System.out.println("rep.getText():=>"+rep.getText());
                        LOG.debug("---response=" + rep.getText());
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
                jsonData.put("entityid", prop.getProperty("entityid"));
                jsonData.put("entitytype", prop.getProperty("entitytype"));
                jsonData.put("log", prop.getProperty("log"));
                jsonData.put("click", prop.getProperty("click"));
                return jsonData;
        }

        private Properties prop;

}
