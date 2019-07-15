package com.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.logging.Logger;

/**
 * Hello FunctionApp
 */
public class WebHello {
  protected static final Logger logger = Logger.getLogger("basic");

  public static JsonObject main(JsonObject args) {

    JsonObject response = new JsonObject();
    JsonPrimitive nameArg = args.getAsJsonPrimitive("name");

    String result;
    if (nameArg == null) {
      result = "Welcome to OpenWhisk";
    } else {
      result = "Hello " + nameArg.getAsString();
    }
    response.addProperty("body", "<html><body><h3>" + result + "</h3></body></html>");


    logger.info("invoked with params:");
    return response;
  }
}
