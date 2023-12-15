package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vttp.ssf.assessment.eventmanagement.models.EventName;
import vttp.ssf.assessment.eventmanagement.models.RegisterName;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private RedisRepository redisRepository;

	// TODO: Task 5
	@GetMapping("/listing")
	public String EventList(Model model) throws JsonMappingException, JsonProcessingException {
		Set<String> myEvents = redisRepository.getAllEvents();
		List<EventName> myList = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();
		System.err.println(myEvents);

		for (String ent : myEvents) {
			// Remove 'EventName(' and ')' from the string
			int iEnt = Integer.parseInt(ent);
			String eventItems = redisRepository.getEvent(iEnt);
			System.out.println("Event: " + eventItems);
			String jsonWithoutClass = eventItems.substring("EventName(".length(), eventItems.length() - 1);
			System.out.println(jsonWithoutClass);
			String[] keyValuePairs = jsonWithoutClass.split(", ");
			EventName eventName = new EventName();
			String temp = keyValuePairs[0].replace("eventId=", "");
			eventName.setEventId(Integer.parseInt(temp));
			temp = keyValuePairs[1].replace("eventName=", "");
			eventName.setEventName(temp);
			temp = keyValuePairs[2].replace("eventSize=", "");
			eventName.setEventSize(Integer.parseInt(temp));
			temp = keyValuePairs[3].replace("eventDate=", "");
			eventName.setEventDate(Long.parseLong(temp));
			temp = keyValuePairs[4].replace("participants=", "");
			eventName.setParticipants(Integer.parseInt(temp));
			myList.add(eventName);
		}
		model.addAttribute("myEvents", myList);
		return "eventlist";
	}

	@GetMapping("/register/{eventId}")
	public String toRegisterEvent(@PathVariable("eventId") String eventId, Model model) {
		System.out.println(" Event ID: " + eventId);
		model.addAttribute("invEvent", new RegisterName());
		EventName eventName = new EventName();

		int iEnt = Integer.parseInt(eventId);
			String eventItems = redisRepository.getEvent(iEnt);
			System.out.println("Event: " + eventItems);
			String jsonWithoutClass = eventItems.substring("EventName(".length(), eventItems.length() - 1);
			System.out.println(jsonWithoutClass);
			String[] keyValuePairs = jsonWithoutClass.split(", ");

			String temp = keyValuePairs[0].replace("eventId=", "");
			eventName.setEventId(Integer.parseInt(temp));
			temp = keyValuePairs[1].replace("eventName=", "");
			eventName.setEventName(temp);
			temp = keyValuePairs[2].replace("eventSize=", "");
			eventName.setEventSize(Integer.parseInt(temp));
			temp = keyValuePairs[3].replace("eventDate=", "");
			eventName.setEventDate(Long.parseLong(temp));
			temp = keyValuePairs[4].replace("participants=", "");
			eventName.setParticipants(Integer.parseInt(temp));

			model.addAttribute("regeventName", eventName.getEventName());
			model.addAttribute("regeventDate", eventName.getEventDate());
			

		return "eventRegister";
	}
	// @GetMapping("/register/")
	// public String toRegisterEvent(Model model) {
	// // System.out.println(model);

	// return "eventRegister";
	// }

}
