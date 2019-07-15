package com.example;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.google.gson.JsonObject;
import com.ibm.cloud.functions.ServiceHelper;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * CreateEngagement
 */
public class CreateEngagement {
    protected static final Logger logger = Logger.getLogger("basic");

    public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();

        EngagementStore store = new EngagementStore(args, "engagements");

        String[] tags = new String[] {"java", "cloud"};
        Engagement jfokus = new Engagement( "JFokus 2019", "Stockholm",new Date());
        jfokus.setTags(tags);
        jfokus = store.persist(jfokus);
        System.out.println(jfokus.get_id());
        System.out.println(jfokus.get_rev());

        response.addProperty("id", jfokus.get_id());
        return response;
    }


}
