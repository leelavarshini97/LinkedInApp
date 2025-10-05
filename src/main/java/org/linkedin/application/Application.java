package org.linkedin.application;

import lombok.Getter;
import lombok.Setter;
import org.linkedin.application.notification.Observer;
import org.linkedin.application.post.Post;
import org.linkedin.application.request.Request;
import org.linkedin.application.search.SearchManager;
import org.linkedin.application.user.User;
import org.linkedin.controller.request.PostRequest;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
public class Application {
    private static Application instance;
    AtomicInteger postID = new AtomicInteger(0);

    private Application(){};

    public static Application getInstance(){
        if(instance == null){
            instance = new Application();
        }
        return instance;
    }

    //users creation

    AtomicInteger userId = new AtomicInteger(-1);
    Database database;

    public void createUser(User user){
        Database.getInstance().addUsers(user);
    }

    public String getUser(int id){
        User user = Database.getInstance().getUser(id);
        return user.getName() + user.getEmail() + user.getNumber() +
                user.getSummary() + user.getMyPost();
    }

    public void updateUserDetail(int id, String name, String email, long number, String summary){
        User user = Database.getInstance().getUser(id);
        if(name != null) user.setName(name);
        else if(email != null) user.setEmail(email);
        else if(number != 0) user.setNumber(number);
        else if(summary != null )user.setSummary(summary);
        Database.getInstance().updateUser(user.getId(), user);

    }

    //search
    public List<String> search(String criteria, String content){
        if(criteria.equals("Job")){
            criteria = "Job";
        }
        else if(criteria.equals("Company")){
            criteria = " Company";
        }
        else if(criteria.equals("People")){
            criteria = "People";
        }
        else if(criteria.equals("Course")){
            criteria = "Course";
        }
        else if(criteria.equals("Post")){
            criteria = "Post";
        }
        else {
            criteria = "All";
        }
        if(!content.isEmpty()) {
            SearchManager search = SearchManager.getInstance();
            return search.findDetails(content, criteria);
        }
        return new ArrayList<>();
    }

    //post
    public Post createPost(int id, PostRequest postBody){
        User user = Database.getInstance().getUser(id);
        Post post = null;
        if(postBody.getType().equals("Thought")){
            post = postBody.getJob();
        }
        else if(postBody.getType().equals("Poll")){
            post = postBody.getPoll();
        }
        else if(postBody.getType().equals("Event")){
            post = postBody.getEvent();
        }
        else if(postBody.getType().equals("Course")){
            post = postBody.getCourse();
        }
        else if(postBody.getType().equals("Job")){
            post = postBody.getJob();
        }
        post.setId(postID.getAndIncrement());
        user.addPost(post);
        post.notifyObserver(post);
        Database.getInstance().addPost(post);
        return post;
    }

    //send request
    public String sendConnectionRequest(int from, int to ){
        User toUser = Database.getInstance().getUser(to);
        User fromUser = Database.getInstance().getUser(from);
        fromUser.sendRequest(toUser);
        return "request sent";
    }

    public List<Integer> getConnectionsList(int id) {
        User user = Database.getInstance().getUser(id);
        return user.getConnections();
    }

    public String updateRequest(int id, String status){
        Request request = Database.getInstance().requestMap.get(id);
        return request.getTo().receiverRequest(id,status);
    }

    //followers
    public String sendFollow(int id, int to) {
        User fromUser = Database.getInstance().getUser(id);
        User toUser = Database.getInstance().getUser(to);
        return toUser.addFollowers(fromUser);
    }

    public List<Observer> getFollowersList(int id) {
        User user = Database.getInstance().userMap.get(id);
        return user.getFollowers();
    }

    //messageUser
    public String send(int from,int to, String content){
        User fromUser = Database.getInstance().userMap.get(from);
        User toUser = Database.getInstance().userMap.get(to);
        return fromUser.sendMessage(toUser, content);
    }
    public List<String> viewMessages(int id, int with){
        User user = Database.getInstance().getUser(id);
        return user.viewMessages(with);
    }

    public List<Post> getPostList() {
        return Database.getInstance().postList;
    }
}
