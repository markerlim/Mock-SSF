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
            System.err.println("Error: The specified file does not exist or is not a valid file: " + fileName);
            System.exit(1);
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String jsonContent = "";

        while ((line = br.readLine()) != null) {
            jsonContent += line;
        }

        JsonReader jsonReader = Json.createReader(new StringReader(jsonContent));
        JsonArray jsonArray = jsonReader.readArray();
        List<Event> listofevents = new ArrayList<>();

        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            Event eObject = new Event();
            eObject.setEventId(jsonObject.getInt("eventId"));
            eObject.setEventName(jsonObject.getString("eventName"));
            eObject.setEventSize(jsonObject.getInt("eventSize"));
            eObject.setEventDate(jsonObject.getJsonNumber("eventDate").longValue());
            eObject.setParticipants(jsonObject.getInt("participants"));
            listofevents.add(eObject);
        }

        br.close();

        return listofevents;
    }

}
