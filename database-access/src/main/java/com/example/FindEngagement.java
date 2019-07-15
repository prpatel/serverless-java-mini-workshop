package com.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.List;

public class FindEngagement {

    public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();

        JsonPrimitive idArg = args.getAsJsonPrimitive("id");
        if (idArg == null) {
            response.addProperty("result", "id not provided");
        } else {
            EngagementStore store = new EngagementStore(args, "engagements");
            Engagement engagement = store.findById(idArg.getAsString());
            if (engagement != null) {
                JsonElement jsonElement = new Gson().toJsonTree(engagement);
                response = jsonElement.getAsJsonObject();
            } else {
                response = response;
            }

        }
        return response;
    }
}
