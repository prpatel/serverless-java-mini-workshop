package com.example;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UpdateEngagementTest {

    @Test
    public void testUpdate() {
        String newLocation = "Shangri-la";
        // create a test item
        JsonObject args = new JsonObject();
        EngagementStore store = new EngagementStore(args, "engagements");
        Engagement tempObj = new Engagement( "TestObj2", "Valhalla",new Date());
        tempObj = store.persist(tempObj);

        Engagement engagement = store.findById(tempObj.get_id());
        // check it's found first
        assertEquals("TestObj2", engagement.getName());

        // set the id
        args.addProperty("id", engagement.get_id());
        args.addProperty("location", newLocation);
        // update it
        JsonObject response = UpdateEngagement.main(args);
        // check the response
        System.out.println(response);
        assertEquals("OK", response.getAsJsonPrimitive("result").getAsString());
        // check in the db if it actually updated
        engagement = store.findById(tempObj.get_id());
        assertEquals(newLocation, engagement.getLocation());
        // cleanup
        store.delete(engagement);
    }
}

