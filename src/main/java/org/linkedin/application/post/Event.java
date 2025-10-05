package org.linkedin.application.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Event extends Post {
    String eventName;
    String description;
    String date;
    String time;

    public String getDetails(){
        return this.getEventName()+ this.getDescription()
                + this.getTime() + this.getDate();
    }
}
