package vttp.ssf.assessment.eventmanagement.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventName implements Serializable{

    private int eventId;
    private String eventName;
    private int eventSize;
    private Long eventDate;
    private int participants;

     // Custom method to format eventDate as "dd-MM-yyyy"
    public String getFormattedEventDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(new Date(eventDate));
    }
}
