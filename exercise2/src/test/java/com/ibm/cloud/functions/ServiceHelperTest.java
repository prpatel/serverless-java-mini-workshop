package com.ibm.cloud.functions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceHelperTest {

    @Test
    public void getCloudCredentials() {
        String jsonString = "{\n" +
                "    \"__bx_creds\": {\n" +
                "      \"cloudantNoSQLDB\": {\n" +
                "        \"apikey\": \"MN-k99NGQD30hoxRoZiVYxiJwWnNH_cH0-ya97rj01EV\",\n" +
                "        \"credentials\": \"cloudant-serverless-creds\",\n" +
                "        \"host\": \"59fedc56-ae8d-46aa-bc01-2b4fd1741271-bluemix.cloudantnosqldb.appdomain.cloud\",\n" +
                "        \"iam_apikey_description\": \"Auto generated apikey during resource-key operation for Instance - crn:v1:bluemix:public:cloudantnosqldb:us-south:a/66b364105e8548fa802bf84f1a936269:0e720880-5782-431f-a90d-1a743e59314e::\",\n" +
                "        \"iam_apikey_name\": \"auto-generated-apikey-43aa3f76-6bf6-4b8a-9c2e-7103064388a6\",\n" +
                "        \"iam_role_crn\": \"crn:v1:bluemix:public:iam::::serviceRole:Manager\",\n" +
                "        \"iam_serviceid_crn\": \"crn:v1:bluemix:public:iam-identity::a/66b364105e8548fa802bf84f1a936269::serviceid:ServiceId-d081ce9d-5b4e-4095-9156-981fe60c597f\",\n" +
                "        \"instance\": \"cloudant-serverless-alias\",\n" +
                "        \"password\": \"45821c744e54a4067a17b7c98cebc23bf19e25bbf6e4d1c0091196f4c4b6f7be\",\n" +
                "        \"port\": 443,\n" +
                "        \"url\": \"https://59fedc56-ae8d-46aa-bc01-2b4fd1741271-bluemix:45821c744e54a4067a17b7c98cebc23bf19e25bbf6e4d1c0091196f4c4b6f7be@59fedc56-ae8d-46aa-bc01-2b4fd1741271-bluemix.cloudantnosqldb.appdomain.cloud\",\n" +
                "        \"username\": \"59fedc56-ae8d-46aa-bc01-2b4fd1741271-bluemix\"\n" +
                "      }\n" +
                "    }\n" +
                "}";

        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(jsonString).getAsJsonObject();
        String[] creds = ServiceHelper.getCloudCredentials(result);

        assertEquals("apikey not equals", "MN-k99NGQD30hoxRoZiVYxiJwWnNH_cH0-ya97rj01EV", creds[0]);
        assertEquals("host not equals", "59fedc56-ae8d-46aa-bc01-2b4fd1741271-bluemix.cloudantnosqldb.appdomain.cloud", creds[1]);
    }

    @Test
    public void getLocalProperties() {
    }
}