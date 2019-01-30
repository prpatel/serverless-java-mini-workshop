package com.example;

import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FindEngagementTest {
    @Test
    public void testFound() {
        JsonObject args = new JsonObject();
        args.addProperty("id", "ae7f8f1331244a63a8b8e26e91176298");
        JsonObject response = FindEngagement.main(args);
        System.out.println(response);
        assertEquals("JFokus 2019", response.getAsJsonPrimitive("name").getAsString());

    }
    @Test
    public void testNotFound() {
        JsonObject args = new JsonObject();
        args.addProperty("id", "blah");
        JsonObject response = FindEngagement.main(args);
        System.out.println(response);
        assertEquals(new JsonObject(), response);

    }
}