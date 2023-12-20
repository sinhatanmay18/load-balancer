package com.loadbalancer.loadbalancer.controller;

import com.loadbalancer.loadbalancer.entity.Server;
import com.loadbalancer.loadbalancer.service.ServerService;
import com.loadbalancer.loadbalancer.service.internal.ServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    @Autowired
    private ServerService serverService;
    @GetMapping("/find-server")
    public ResponseEntity<Server> findServer(@RequestParam String userName, @RequestParam String email){
        return ResponseEntity.ok(this.serverService.findServer(userName,email));
    }
}
