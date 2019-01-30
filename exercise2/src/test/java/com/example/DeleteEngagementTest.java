package com.example;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DeleteEngagementTest {

    @Test
    public void testFound() {
        // create a test item
        JsonObject args = new JsonObject();
        EngagementStore store = new EngagementStore(args, "engagements");
        Engagement tempObj = new Engagement( "TestObj", "Valhalla",new Date());
        tempObj = store.persist(tempObj);

        Engagement engagement = store.findById(tempObj.get_id());
        // check it's found first
        assertEquals("TestObj", engagement.getName());

        // set the id
        args.addProperty("id", engagement.get_id());
        // delete it
        JsonObject response = DeleteEngagement.main(args);
        // check the reponse
        System.out.println(response);
        assertEquals("OK", response.getAsJsonPrimitive("result").getAsString());

    }
}

