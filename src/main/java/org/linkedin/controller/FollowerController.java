package org.linkedin.controller;

import org.linkedin.application.Application;
import org.linkedin.application.notification.Observer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowerController {

    @PostMapping("/users/{id}/followers")
    public String sendConnectionRequest(int id, @RequestParam int to){
        return Application.getInstance().sendFollow(id, to);
    }

    @GetMapping("/users/{id}/followers")
    public List<Observer> getConnection(int id){
        return Application.getInstance().getFollowersList(id);
    }

}
