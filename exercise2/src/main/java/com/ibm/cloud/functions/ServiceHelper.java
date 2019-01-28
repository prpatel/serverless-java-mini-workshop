package com.ibm.cloud.functions;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ServiceHelper {

    private static CloudantClient cloudant;

    public static CloudantClient createClient(JsonObject params) {

        if (cloudant != null) {
            System.out.println("Instance already connected to Cloudant");
            return cloudant;
        }

        String[] cloudantCredentials = getCloudCredentials(params);
        if (cloudantCredentials != null) {

        } else {
            System.out.println("Running locally. Looking for credentials in cloudant.properties");
            String apikey = getLocalProperties("ibmcloud.properties").getProperty("cloudant_apikey");
            String host = getLocalProperties("ibmcloud.properties").getProperty("cloudant_host");
            if (apikey == null || apikey.length() == 0) {
                System.out.println("To use a database, set the Cloudant url in src/main/resources/cloudant.properties");
                return null;
            }
            cloudantCredentials[0] = apikey;
            cloudantCredentials[1] = host;
        }

        try {
            System.out.println("Connecting to Cloudant");
            cloudant = ClientBuilder.url(new URL("https://"+cloudantCredentials[1]))
                    .iamApiKey(cloudantCredentials[0])
                    .build();
            return cloudant;
        } catch (Exception e) {
            System.out.println("Unable to connect to database");
            //e.printStackTrace();
            return null;
        }
    }

    public static String[] getCloudCredentials(JsonObject params) {
        // __bx_creds.cloudantNoSQLDB.apikey, host: params.__bx_creds.cloudantNoSQLDB.host
        JsonObject paramsObj = params.getAsJsonObject();
        try {
            JsonElement creds = paramsObj.get("__bx_creds");
            JsonElement cloudantElement = creds.getAsJsonObject().get("cloudantNoSQLDB");
            JsonElement cloudantAPIKeyElement = cloudantElement.getAsJsonObject().get("apikey");
            JsonElement cloudantHostElement = cloudantElement.getAsJsonObject().get("host");
            return new String[]{cloudantAPIKeyElement.getAsJsonPrimitive().getAsString(), cloudantHostElement.getAsJsonPrimitive().getAsString()};
        } catch (Exception e)  {
            return null;
        }

    }

    public static Properties getLocalProperties(String fileName) {
        Properties properties = new Properties();
        InputStream inputStream = ServiceHelper.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
