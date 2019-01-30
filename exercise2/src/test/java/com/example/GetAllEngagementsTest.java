package com.example;

import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetAllEngagementsTest {
    @Test
    public void testFunction() {
        JsonObject args = new JsonObject();
        JsonObject response = GetAllEngagements.main(args);
        System.out.println(response);
    }


}