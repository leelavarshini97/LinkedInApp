package org.linkedin.controller;

import org.linkedin.application.Application;
import org.linkedin.controller.request.MessageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @PostMapping("/users/{id}/messages")
    public String sendConnectionRequest(@PathVariable int id, @RequestBody MessageRequest messageRequest){
        return Application.getInstance().send(id, messageRequest.to, messageRequest.content);
    }

    @GetMapping("/users/{id}/messages")
    public List<String> viewMessages(@PathVariable int id, @RequestParam("with") int with){
        return Application.getInstance().viewMessages(id, with);
    }
}
