package org.linkedin.application.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Thought extends Post {
    String description;

    public Thought(String thought){
        description = thought;
    }

    public String getDetails(){
        return this.getDescription();
    }
}
