package com.example;
import com.cloudant.client.api.CloudantClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.cloud.functions.ServiceHelper;

import java.util.List;
import java.util.logging.Logger;

/**
 * List all cloudant db's
 */
public class ListAllDBs {
    protected static final Logger logger = Logger.getLogger("basic");

    public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        // connect to cloudant using IAM
        CloudantClient client =  ServiceHelper.createClient(args);


        List<String> databases = client.getAllDbs();
        JsonArray databasesJson = new JsonArray();

        System.out.println("All my databases : ");
        for ( String db : databases ) {
            databasesJson.add(db);
        }
        response.add("databases", databasesJson);
        return response;
    }


}
