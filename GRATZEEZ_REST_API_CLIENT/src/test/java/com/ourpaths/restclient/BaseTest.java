//This code is the property of Myriata Inc.
//Copyright Myriata Inc.

package com.ourpaths.restclient;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;

import com.ourpaths.restclient.util.SecurityUtils;

/**
 * This is base class for all other integration-test classes which shares common
 * methods.
 */
public class BaseTest {

        protected static int httpsPort;
        protected static String truststorePath;

        /**
         * This method generates URI including all required parameters with its
         * proper values.
         */
        protected String generateURIParameters(String apiKey, String apiSecret)
                        throws Exception {
                String authToken = doLogin();
                String apiSignature = SecurityUtils.getMD5(apiKey + apiSecret);// key,
                                                                               // input
                apiKey = URLEncoder.encode(apiKey, "UTF-8");
                apiSignature = URLEncoder.encode(apiSignature, "UTF-8");
                authToken = URLEncoder.encode(authToken, "UTF-8");
                String uriParameters = "?api-key=" + apiKey + "&signature="
                                + apiSignature + "&performer-token="
                                + authToken;
                return uriParameters;
        }

        /**
         * This method creates Client object to call REST-API with SSL support
         * and required certificate.
         */
        protected Client getRestletClient() {
                Component comp = new Component();
                comp.getClients().add(Protocol.HTTP);
//                Server server = comp.getServers()
//                                .add(Protocol.HTTPS, httpsPort);
                Server server = comp.getServers()
                        .add(Protocol.HTTP, 8080);
                server.getContext().getParameters();
//                parameter.add("truststorePath", truststorePath);
//                Client client = new Client(server.getContext(), Protocol.HTTPS);
                Client client = new Client(server.getContext(), Protocol.HTTP);
                return client;
        }

        /**
         * This method loads all key-value pairs from given property file-name.
         */
        protected Properties loadPropertyFile(String propertyFile)
                        throws IOException, FileNotFoundException {
                Properties prop = new Properties();
                File f = new File(propertyFile);
                prop.load(new FileReader(f));
                return prop;
        }
        
        /**
         * Reads the content of file provided at given path and return as String
         * @param filePath
         * @return
         */
        protected String getDataFromFile(String filePath)
        {
                StringBuffer sb = new StringBuffer("");
                try
                {
                        FileReader fr = new FileReader(filePath);
                        BufferedReader br = new BufferedReader(fr);
        
                        String line = null;
                        do
                        {
                                line = br.readLine();
                                if (line != null)
                                {
                                        sb.append(line);
                                }
        
                        } while (line != null);
                } catch (FileNotFoundException e)
                {
                        e.printStackTrace();
                } catch (IOException e)
                {
                        e.printStackTrace();
                }
                return sb.toString();
        }

        
        /**
         * This method does login into application to get proper
         * authentication-token and returns it.
         */
        private String doLogin() throws IOException {
                AuthenticatorTest authenticatorTest = new AuthenticatorTest();
                authenticatorTest.setup();
                Representation representation = authenticatorTest.login();
                                               String response = representation.getText();
                JSONObject loginResponse = JSONObject.fromObject(response);
                String authToken = loginResponse
                                .getString("authentication-token");
                assertNotNull(authToken);
                return authToken;
        }

}
