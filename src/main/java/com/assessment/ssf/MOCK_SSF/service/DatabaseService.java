package com.assessment.ssf.MOCK_SSF.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.assessment.ssf.MOCK_SSF.model.Event;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class DatabaseService {

    public static List<Event> readFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("The specified file does not exist or is not a valid file: " + fileName);
        }

        // Use try-with-resources to automatically close the resources
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
        }

        // Parse the JSON content
        JsonReader jsonReader = Json.createReader(new StringReader(jsonContent.toString()));
        JsonArray jsonArray = jsonReader.readArray();
        List<Event> listOfEvents = new ArrayList<>();

        // Convert JSON array into Event objects
        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            Event eObject = new Event();
            eObject.setEventId(jsonObject.getInt("eventId"));
            eObject.setEventName(jsonObject.getString("eventName"));
            eObject.setEventSize(jsonObject.getInt("eventSize"));
            eObject.setEventDate(jsonObject.getJsonNumber("eventDate").longValue());
            eObject.setParticipants(jsonObject.getInt("participants"));
            listOfEvents.add(eObject);
        }

        return listOfEvents;
    }
}
