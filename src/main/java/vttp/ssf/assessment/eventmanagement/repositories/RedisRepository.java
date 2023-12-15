package vttp.ssf.assessment.eventmanagement.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;

import vttp.ssf.assessment.eventmanagement.models.EventName;

@Repository
public class RedisRepository {

	@Autowired
	@Qualifier("myEventRedis")
	private RedisTemplate<String, String> template;
	// TODO: Task 2

	public void saveRecord(String id, EventName eventDetail) {
		template.opsForValue()
				.set(id, eventDetail.toString());
	}

	// TODO: Task 3
	public int getNumberOfEvents() {
		Set<String> opsValue = template.keys("*");
		// int size = opsValue.size();

		// for (String string : opsValue) {
		// System.out.println(string);
		// }
		return opsValue.size();
	}

		public Set<String> getAllEvents() {
		Set<String> opsValue = template.keys("*");
		// int size = opsValue.size();

		// for (String string : opsValue) {
		// System.out.println(string);
		// }
		return opsValue;
	}
	// TODO: Task 4
	public String getEvent(Integer index) {
		String stringValue = String.format("%d", index);
		String value = template.opsForValue().get(stringValue);
		return value;
	}
}
