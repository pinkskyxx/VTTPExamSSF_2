package vttp.ssf.assessment.eventmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
// import jakarta.json.stream.JsonParser.Event;
import vttp.ssf.assessment.eventmanagement.models.EventName;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

	@Autowired
  	private DatabaseService databaseService;

	@Autowired
	private RedisRepository redisRepository;
	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	// TODO: Task 1
	@Override
	public void run(String... args) throws Exception {
		System.out.printf(">>> Event template: ");
		String pathFileName = "events.json"; // for windows users

		
		List<EventName> det =   databaseService.readFile(pathFileName);

		// System.out.println(det);
				// System.out.println("Event object: " + event);
		for (EventName jsonValue : det) {
			System.out.println(jsonValue);
			int evn_id = jsonValue.getEventId();
			String stringValue = String.format("%d", evn_id);
			redisRepository.saveRecord(stringValue, jsonValue);
			System.out.println("Write to redis: "+ jsonValue.toString());
		}
	
	}
}
