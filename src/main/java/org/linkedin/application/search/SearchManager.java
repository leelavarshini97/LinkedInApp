package org.linkedin.application.search;

import org.linkedin.application.Database;
import org.linkedin.application.post.Course;
import org.linkedin.application.post.Job;
import org.linkedin.application.user.User;

import java.util.*;

public class SearchManager {
    private static SearchManager instance;
    private SearchManager(){};
    public static SearchManager getInstance(){
        if(instance == null){
            instance = new SearchManager();
        }
        return instance;
    }

    public List<String> findDetails(String searchThis, String criteria) {
        List<String> list = new ArrayList<>();
        if(criteria.equals("Company")){
            list.addAll(getCompanyDetails(searchThis));
        }
        else if(criteria.equals("Course")){
            list.addAll(getCourseDetails(searchThis));
        }
        else if (criteria.equals("Job")) {
            list.addAll(getJobDetails(searchThis));
        }
        else if(criteria.equals("People")){
            list.addAll(getPeopleDetails(searchThis));
        }
        else{
            list.addAll(getJobDetails(searchThis ));
            list.addAll(getPeopleDetails(searchThis));
            list.addAll(getCourseDetails(searchThis));
            list.addAll(getCompanyDetails(searchThis));
        }
        return list;
    }

    public List<String> getCompanyDetails(String searchThis){
        List<String> list = new ArrayList<>();
        for(Map.Entry<Integer, User> entry : Database.getInstance().userMap.entrySet()){
            if(entry.getValue().equals(searchThis)){
                list.add(searchThis);
            }
        }
        return list;
    }

    public List<String> getCourseDetails(String searchThis){
    List<String> list = new ArrayList<>();
        for(Course c : Database.getInstance().courses){
            if(c.getName().equals(searchThis)){
                list.add(c.getDetails());
            }
        }
        return list;
    }

    public List<String> getJobDetails(String searchThis){
        List<String> list = new ArrayList<>();
        for(Job j : Database.getInstance().jobs){
            if(j.getRoleName().equals(searchThis)){
                list.add(j.getDetails());
            }
        }
        return list;
    }

    public List<String> getPeopleDetails(String searchThis){
        List<String> list = new ArrayList<>();
        for(Map.Entry<Integer, User> ent : Database.getInstance().userMap.entrySet()){
            if(ent.getValue().getName().equals(searchThis)){
                list.add(searchThis);
            }
        }
        return list;
    }

}
