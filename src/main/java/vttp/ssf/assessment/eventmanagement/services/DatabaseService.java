package vttp.ssf.assessment.eventmanagement.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.ssf.assessment.eventmanagement.models.EventName;

@Service
public class DatabaseService {
    
    // TODO: Task 1
    public List<EventName> readFile(String filename) throws FileNotFoundException{

        // EventName event= (EventName) new EventName();

      String pathFileName = filename; // for windows users

		File file = new File(pathFileName);
		InputStream is = new FileInputStream(file);

		List<EventName> event = new ArrayList<>();
		JsonReader jsonReader = Json.createReader(is);
		JsonArray jsonArray1 = jsonReader.readArray();
		for (JsonValue jsonValue : jsonArray1) {
			JsonObject jsonObject = jsonValue.asJsonObject();

			EventName eventName = new EventName();
			eventName.setEventId(Integer.valueOf(jsonObject.get("eventId").toString()));
			eventName.setEventName(jsonObject.get("eventId").toString());
			eventName.setEventDate(Long.valueOf(jsonObject.get("eventDate").toString()));
			eventName.setParticipants(Integer.valueOf(jsonObject.get("participants").toString()));
			eventName.setEventId(Integer.valueOf(jsonObject.get("eventId").toString()));
			event.add(eventName);
		}
		// System.out.println("Event object: " + event);
		// for (EventName jsonValue : event) {
		// 	System.out.println(jsonValue);
			
		// }
          return event;
    }
}
