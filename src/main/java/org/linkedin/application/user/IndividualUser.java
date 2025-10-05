package org.linkedin.application.user;
import lombok.*;
import org.linkedin.application.message.Message;

import java.util.*;

@Getter
@Setter
public class IndividualUser extends User {
    List<Skill> skills = new ArrayList<>();
    List<Message> messageList = new ArrayList<>();


    public void updateSkills(String[] skills){
        for(String s : skills){
            this.skills.add(Skill.valueOf(s));
        }
    }


    public String printDetails() {
        return this.getName() + " " + this.getNumber() + " "
                + this.getEmail() + " " + this.getSkills() + " " + this.getMyPost();
    }



}
