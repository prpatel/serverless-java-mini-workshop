package com.example;

import com.google.gson.JsonObject;
import com.ibm.cloud.functions.CloudantObjectStore;

public class EngagementStore extends CloudantObjectStore<Engagement> {

    public EngagementStore(JsonObject args, String dbName) {
        super( args, dbName);
    }

}
