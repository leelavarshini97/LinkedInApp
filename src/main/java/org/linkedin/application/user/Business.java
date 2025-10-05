package org.linkedin.application.user;
import lombok.*;
import org.linkedin.application.post.Job;

import java.util.*;
@Getter
@Setter
public class Business extends User {
    List<Job> jobList = new ArrayList<>();


    public String printDetails(){
        return this.getName() + this.getNumber() +
                this.getSummary() + this.getJobList();
    }



    //needs job listing
}
