package org.linkedin.controller;

import org.linkedin.application.Application;
import org.linkedin.application.post.Post;
import org.linkedin.application.user.User;
import org.linkedin.controller.request.PostRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PostController {

    @PostMapping("/posts")
    public String createPost(@RequestBody PostRequest postBody, int id){
        Post post = Application.getInstance().createPost(id,postBody);
        return post.printDetails();
    }

    @GetMapping("/posts")
    public List<Post> getPost(@RequestParam String details){
        return Application.getInstance().getPostList();
    }
    @PutMapping("/posts/{id}")
    public String updatePost(@PathVariable int id, User user, @RequestBody PostRequest postBody){
        Post post = postBody.getPost();
        user.setPost(id, post);
        return user.getPost(id).printDetails();
    }

    @DeleteMapping("/posts/{id}")
    public List<String> delete(@PathVariable int id, User user, @RequestBody PostRequest postBody){
        Post post = postBody.getPost();
        List<String> posts = new ArrayList<>();
        user.deletePost(id);
        for(int i = 1; i < user.getMyPost().size(); i++){
            user.getPost(i).printDetails();
        }
        return posts;
    }
}
