package org.linkedin.application.post;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class Course extends Post {
    String name;
    String description;
    List<String> tutors;
    int star;
    public Course(String name, String description, List<String> tutors){
        this.name = name;
        this.description = description;
        this.tutors = tutors;
    }
    public void setStar(int star){
        Scanner scanner = new Scanner(System.in);
        System.out.println("what would the rating out of 5 stars");
        this.star = star;
    }

    public String getDetails(){
        return this.getDescription() + " " + this.getName() + " " + this.getTutors();
    }
}
