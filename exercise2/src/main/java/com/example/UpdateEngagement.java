package com.example;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 * CreateEngagement
 */
public class UpdateEngagement {
    protected static final Logger logger = Logger.getLogger("basic");

    public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();

        JsonPrimitive idArg = args.getAsJsonPrimitive("id");
        if (idArg == null) {
            response.addProperty("result", "id not provided");
        } else {
            EngagementStore store = new EngagementStore(args, "engagements");
            Engagement engagement = store.findById(idArg.getAsString());
            if (engagement != null) {
                // update now, but create a new object, the id and rev will get copied
                Engagement newEngagement = store.cloneObject(engagement);

                JsonPrimitive nameArg = args.getAsJsonPrimitive("name");
                if (nameArg != null) {
                    newEngagement.setName(nameArg.getAsString());
                }
                JsonPrimitive locationArg = args.getAsJsonPrimitive("location");
                if (locationArg != null) {
                    newEngagement.setLocation(locationArg.getAsString());
                }
                JsonPrimitive dateArg = args.getAsJsonPrimitive("date");
                if (dateArg != null) {
                    DateFormat df = DateFormat.getDateInstance();
                    // this needs to do better error checking
                    // also needs proper dates parsing
                    // and return a fail if it doesnt work?
                    Date date;
                    try {
                        date = df.parse(dateArg.getAsString());
                        newEngagement.setDate(date);
                    } catch ( ParseException e) {
                        response.addProperty("warning", "Unable to parse date");
                    }
                }
                store.update(engagement.get_id(),newEngagement);
                response.addProperty("result", "OK");
            } else {
                response.addProperty("result", "object not found");
            }
        }
        return response;
    }


}
