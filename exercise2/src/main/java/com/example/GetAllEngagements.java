package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GetAllEngagements {

    public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        EngagementStore store = new EngagementStore(args, "engagements");

        Collection<Engagement> list = store.findAll();
        System.out.println("Engagements count: " + store.count());
        System.out.println("All Engagements : ");
        JsonArray jsonArray = (JsonArray) new Gson().toJsonTree(list,
                new TypeToken<List<Engagement>>() {
                }.getType());
        response.add("result", jsonArray);

        return response;
    }
}
