package org.linkedin.controller;

import org.linkedin.application.Application;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ConnectionController {

    @PostMapping("/users/{id}/connections")
    public String sendConnectionRequest(@PathVariable int id, @RequestBody Map<String, Integer> to){
        return Application.getInstance().sendConnectionRequest(id, to.get("to"));
    }

    @GetMapping("/users/{id}/connections")
    public List<Integer> getConnection(@PathVariable int id){
        return Application.getInstance().getConnectionsList(id);
    }
    @PutMapping("/users/{id}/connections/{conn_id}")
    public String updateConnectionRequest( @PathVariable int conn_id,
                                           @RequestBody Map<String, String> status){
        return Application.getInstance().updateRequest(conn_id, status.get("status") );
    }
}
