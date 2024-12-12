package com.assessment.ssf.MOCK_SSF.service;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.assessment.ssf.MOCK_SSF.model.Event;

public class DatabaseService {

    public static List<Event> readFile(String fileName) throws IOException {
        List<Event> eventsList = new ArrayList<>();

        // Create a JsonReader instance to read the file
        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            // Parse the file as a JsonArray
            JsonArray jsonArray = reader.readArray();

            // Iterate over the array and parse each object
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject eventObject = jsonArray.getJsonObject(i);

                // Create a new Event instance from the parsed data
                Event event = new Event();
                event.setEventId(eventObject.getInt("eventId"));
                event.setEventName(eventObject.getString("eventName"));
                event.setEventSize(eventObject.getInt("eventSize"));
                event.setEventDate(eventObject.getJsonNumber("eventDate").longValue());
                event.setParticipants(eventObject.getInt("participants"));

                // Add the event to the list
                eventsList.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error reading the events.json file.", e);
        }

        return eventsList;
    }
}
