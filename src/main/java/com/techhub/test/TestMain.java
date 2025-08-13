package com.techhub.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techhub.grpcdemo.dto.PaperData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {

    private static final String JSON = "{    \"elements\": [        {            \"elementType\": \"TEXT\",            \"data\": \"This is text data\",            \"props\": {                \"fontStyle\": \"NORMAL\",                \"fontSize\": 22,                \"fontName\": \"Areal\",                \"alignment\": \"LEFT\"            }        },        {            \"elementType\": \"IMAGE\",            \"data\": true,            \"props\": {                \"fontStyle\": \"ITALIC\",                \"fontSize\": 22,                \"fontName\": \"Areal\",                \"alignment\": \"RIGHT\"            }        },        {            \"elementType\": \"TABLE\",            \"data\": 3456,            \"props\": {                \"fontStyle\": \"BOLD\",                \"fontSize\": 22,                \"fontName\": \"Areal\",                \"alignment\": \"CENTER\"            }        }    ]}\n";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) {
        try {
            PaperData paperData = OBJECT_MAPPER.readValue(JSON, PaperData.class);
            log.info("PaperData \n - {}\n", paperData);
            printJson(JSON);
        } catch (Exception exception) {
            log.error(":)", exception);
        }
    }

    private static void printJson(String json) {
        String formatedJSON = JSON.replace("\n", "");
        log.info("JSON =\n{}\n", formatedJSON);
    }
}
