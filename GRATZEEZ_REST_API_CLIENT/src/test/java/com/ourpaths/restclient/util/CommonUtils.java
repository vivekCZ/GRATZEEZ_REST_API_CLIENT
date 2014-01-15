//This code is the property of Myriata Inc.
//Copyright Myriata Inc.

package com.ourpaths.restclient.util;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * This Class contains common Utility methods.
 */

public class CommonUtils
{

        /**
         * converts data from xml format into Json format
         * 
         * @param xmlValue
         * @return String
         */
        public static String xmlToJsonFormat(String xmlValue)
        {
                XMLSerializer xmlSerializer = new XMLSerializer();
                JSON json = xmlSerializer.read(xmlValue);
                String jsonValue = json.toString();
                return jsonValue;
        }

        /**
         * converts data from Json format into xml format
         * 
         * @param jsonData
         * @return String
         */
        public static String jsonToXmlFormat(String jsonData)
        {
                XMLSerializer xmlSerializer = new XMLSerializer();
                JSON json = JSONSerializer.toJSON(jsonData);
                String xmlValue = xmlSerializer.write(json);
                return xmlValue;
        }

}
