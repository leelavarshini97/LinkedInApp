package org.linkedin.controller;
import org.linkedin.application.*;
import org.linkedin.application.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @PostMapping("/users")
    public String createUser(@RequestBody User user){
        Application.getInstance().createUser(user);
        return user.getId() + " " + user.getName() +
                user.getNumber() + user.getEmail() + user.getSummary();
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable int id){
        return Application.getInstance().getUser(id);
    }
    @GetMapping("/users")
    public List<String> getUserDetails(@RequestParam String name, @RequestParam(required = false)String criteria){
        return Application.getInstance().search(criteria, name);

    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable int id, @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) long number,
                             @RequestParam(required = false) String summary){
        Application.getInstance().updateUserDetail(id, name, email, number, summary);

        return Application.getInstance().getUser(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<String> getUserPostList(@PathVariable int id){
        List<String> posts = new ArrayList<>();
        User user = Database.getInstance().getUser(id);
        for(int i = 1; i < user.getMyPost().size(); i++){
            posts.add(user.getPost(i).printDetails());
        }
        return posts;
    }


}
