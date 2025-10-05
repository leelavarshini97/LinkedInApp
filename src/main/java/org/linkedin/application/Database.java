package org.linkedin.application;

import org.linkedin.application.post.Course;
import org.linkedin.application.post.Job;
import org.linkedin.application.post.Post;
import org.linkedin.application.request.Request;
import org.linkedin.application.user.Business;
import org.linkedin.application.user.IndividualUser;
import org.linkedin.application.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {
    private static Database instance;
    private Database(){};

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public Map<Integer, User> userMap = new HashMap<>();
    List<Post> postList = new ArrayList<>();
    public List<Course> courses = new ArrayList<>();
    public List<Job> jobs = new ArrayList<>();
    List<Business>companies = new ArrayList<>();
    List<IndividualUser>people = new ArrayList<>();
    public Map<Integer, Request> requestMap = new HashMap<>();
    AtomicInteger userId = new AtomicInteger(-1);

    public void addUsers(User user){
        user.setId(userId.getAndIncrement());
        userMap.put(user.getId(),user);
    }
    public void updateUser(int id, User user){
        userMap.put(id,user);
    }
    public void removeUsers(int id){
        userMap.remove(id);
    }
    public User getUser(int id){
        return userMap.get(id);
    }

    public void addPost(Post post) {
        postList.add(post);
    }

    public void addRequest(int i, Request request) {
        requestMap.put(i, request);
    }
    public void updateRequest(int i, Request request){
        requestMap.put(i, request);
    }
}
