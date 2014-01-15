//This code is the property of Myriata Inc.
//Copyright Myriata Inc.

package com.ourpaths.restclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Client;
import org.restlet.data.Disposition;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

/**
 * This class contains integration test-cases for create new log entry and list
 * existing log entries.
 */
public class ShowObjectTest extends BaseTest
{

        private static final Log LOG = LogFactory.getLog(ShowObjectTest.class);

        @Before
        public void setup() throws IOException
        {
                prop = loadPropertyFile("ShowObject.properties");
        }

        @After
        public void teardown() throws IOException
        {
                prop.clear();
                prop = null;
        }

        /**
         * This method calls REST API storeShowObjectAssets.
         */
        @Test
        public void storeShowObjectAssets()
        {
                LOG.debug(ShowObjectTest.class + ": storeShowObjectAssets()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        uriParameters += /*"&UserId="
                                        + prop.getProperty("UserId")
                                        +*/ "&VenueId="
                                        + prop.getProperty("VenueId")
                                        + "&ShowObjectType="
                                        + prop.getProperty("ShowObjectType")
                        				+ "&EventStartDate="
                        				+ prop.getProperty("EventStartDate")
                        				+ "&EventEndDate="
                        				+ prop.getProperty("EventEndDate");

                        Client client = getRestletClient();
                        String uri = prop
                                        .getProperty("api_uri_store_show_object_assets")
                                        + "/"
                                        + prop.getProperty("ShowObjectId")
                                        + "/"
                                        + prop.getProperty("AssetId")
                                        + uriParameters;
                        
                        ClientResource service = new ClientResource(uri);
                        service.setNext(client);
                        File file = new File("E:/"
                                        + prop.getProperty("AssetId"));
                        FileRepresentation fileEntity = new FileRepresentation(
                                        file, MediaType.ALL);
                        Representation rep = null;
                        Form fileForm = new Form();
                        fileForm.add(Disposition.NAME_FILENAME, file.getName());
                        Disposition disposition = new Disposition(
                                        Disposition.TYPE_INLINE, fileForm);
                        fileEntity.setDisposition(disposition);
                        rep = service.post(fileEntity);
                        String response = rep.getText();
                        LOG.debug("---request uri=" + uri);
                        System.out.println("---response=" + response);

                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }

        /**
         * This method tests REST-API to retrieve Assets list for Show Object
         */
        @Test
        public void retrieveShowObjectAssetsList()
        {
                LOG.debug(ShowObjectTest.class
                                + ": retrieveShowObjectAssetsList()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        uriParameters += /*
                                          * "&UserId=" +
                                          * prop.getProperty("UserId") +
                                          */"&VenueId="
                                        + prop.getProperty("VenueId")
                                        /*
                                         * + "&ShowObjectType=" +
                                         * prop.getProperty("ShowObjectType")
                                         */
                                        + "&AssetId="
                                        + prop.getProperty("AssetId")
                                        + "&userid-list="
                                        + prop.getProperty("userid-list")
                                        + "&entry-start-date="
                                        + prop.getProperty("entry-start-date")
                                        + "&entry-end-date="
                                        + prop.getProperty("entry-end-date")
                        				+ "&event-start-date="
                        				+ prop.getProperty("EventStartDate")
                        				+ "&event-end-date="
                        				+ prop.getProperty("EventEndDate")
                        				+ "&keywords="
                        				+ prop.getProperty("keywords");

                        Client client = getRestletClient();
                        String uri = prop
                                        .getProperty("api_uri_retrieve_show_object_assets_list")
                                        + "/"
                                        + prop.getProperty("ShowObjectId")
                                        + uriParameters;
                        System.out.println("uri:"+uri);
                        ClientResource service = new ClientResource(uri);
                        service.setNext(client);
                        Representation rep = service.get();
                        LOG.debug("---request uri=" + uri);
                        System.out.println("---response=" + rep.getText());
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }

        /**
         * This method tests REST-API to retrieve Asset object for given
         * criteria
         */
        @Test
        public void retrieveShowObjectAsset()
        {
                LOG.debug(ShowObjectTest.class + ": retrieveShowObjectAsset()");
                try
                {
                        String uriParameters = generateURIParameters(
                                        prop.getProperty("api-key"),
                                        prop.getProperty("api-secret"));
                        uriParameters += /*
                                          * "&UserId=" +
                                          * prop.getProperty("UserId") +
                                          */"&VenueId="
                                        + prop.getProperty("VenueId");

                        Client client = getRestletClient();
                        String uri = prop
                                        .getProperty("api_uri_retrieve_show_object_asset")
                                        + "/"
                                        + prop.getProperty("ShowObjectId")
                                        + "/"
                                        + prop.getProperty("AssetId")
                                        + uriParameters + "&DateTime="+ prop.getProperty("DateTime");
                        ClientResource service = new ClientResource(uri);
                        service.setNext(client);
                        Representation rep = service.get();
                        if (rep != null)
                        {
                                BufferedInputStream in = new BufferedInputStream(
                                                rep.getStream());
                                File file = new File("E:/logs/"
                                                + prop.getProperty("AssetId"));
                                BufferedOutputStream out = new BufferedOutputStream(
                                                new FileOutputStream(file));
                                byte[] buf = new byte[1024];
                                int size = 0;
                                while ((size = in.read(buf)) != -1)
                                {
                                        out.write(buf, 0, size);
                                }
                                out.close();
                                in.close();
                        }
                        LOG.debug("---request uri=" + uri);
                        LOG.debug("---response=" + rep.getText());
                } catch (Exception e)
                {
                        LOG.error(e.toString(), e);
                        Assert.fail();
                }
        }

        private Properties prop;

}
