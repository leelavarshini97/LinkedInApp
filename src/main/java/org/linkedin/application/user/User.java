package org.linkedin.application.user;
import lombok.Getter;
import lombok.Setter;
import org.linkedin.application.Database;
import org.linkedin.application.notification.Observer;
import org.linkedin.application.post.Post;
import org.linkedin.application.request.Request;
import org.linkedin.application.notification.Observable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Component
public abstract class User extends Observable implements Observer {
    int id;
    String name;
    String email;
    long number;
    String summary;
    UserType type;
    Map<Integer, Post> myPost = new HashMap<>();
    List<Observer> followers = new ArrayList<>();
    List<Integer> connections = new ArrayList<>();
    Map<Integer,List<String>> messageMap = new HashMap<>();

    public User(){}

    public void addPost(Post post){
        this.myPost.put(id, post);
    }

    public void update(Object post){
        System.out.println(post);
    }


    public void setPost(int id, Post post) {
        myPost.put(id,post);
    }

    public Post getPost(int id) {
        return myPost.get(id);
    }

    public void deletePost(int id) {
        myPost.remove(id);
    }


    //request information

    List<Request> acceptedList = new ArrayList<>();
    List<Request> declinedList = new ArrayList<>();
    List<Request> pendingList = new ArrayList<>();
    AtomicInteger requestId = new AtomicInteger(1);

    public void sendRequest(User to){
        Request request = new Request(this, to);
        this.pendingList.add(request);
        to.pendingList.add(request);
        Database.getInstance().addRequest(requestId.incrementAndGet(), request);
    }
    AtomicInteger connectionId = new AtomicInteger(1);

    public String receiverRequest(int id, String status) {
        Request request = Database.getInstance().requestMap.get(id);
        if (status.equals("Accept")) {
            request.acceptRequest();
            Database.getInstance().updateRequest(id, request);
            request.getFrom().addConnection(this);
            request.getTo().addConnection(request.getFrom());
            request.getFrom().update("Your connection accepted by " + this.getName());
            acceptedList.add(request);
            request.getTo().pendingList.remove(request);
            return "Accepted";
        } else{
            request.declineRequest();
            declinedList.add(request);
            request.getTo().pendingList.remove(request);
            request.getFrom().pendingList.remove(request);
            Database.getInstance().updateRequest(id, request);
            request.getFrom().update("Your connection declined by " + this.getName());
            return "Declined";
        }
    }

    public List<Integer> getConnections(){
        List<Integer> list = new ArrayList<>();
        for(Integer observer: this.connections){
            list.add(observer);
        }
        return list;
    }

    public String addFollowers(Observer o){
        this.followers.add(o);
        return "following";
    }

    public void addConnection(User observer){
        this.connections.add(observer.id);
        observer.update("Connection accepted");
    }
    public void removeConnection(User observer){
        this.connections.remove(observer.id);
        observer.update("Connection removed");
    }

    AtomicInteger messageID = new AtomicInteger(1);

    public String sendMessage(User to, String content) {
        messageMap.putIfAbsent(to.id, new ArrayList<>());
        messageMap.get(to.id).add("You: " + content);

        to.receiveMessage(this, content);

        return "Message sent from " + this.name + " to " + to.name;
    }
    public void receiveMessage(User fromUser, String content) {
        messageMap.putIfAbsent(fromUser.id, new ArrayList<>());
        messageMap.get(fromUser.id).add(fromUser + ": " + content);
    }
    public List<String> viewMessages(int withUser) {
        return messageMap.getOrDefault(withUser, new ArrayList<>());
    }
}



