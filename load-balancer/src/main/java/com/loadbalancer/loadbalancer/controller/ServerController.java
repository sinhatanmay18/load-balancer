package com.loadbalancer.loadbalancer.controller;

import com.loadbalancer.loadbalancer.entity.Server;
import com.loadbalancer.loadbalancer.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/servers")
public class ServerController {
    @Autowired
    private ServerService serverService;
    @PostMapping("/add-server")
    public ResponseEntity<Boolean> addServers(@RequestBody Server server){
        boolean isAdded = this.serverService.mapServer(server);
        return ResponseEntity.ok(isAdded);
    }
}
