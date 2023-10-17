package com.loadbalancer.loadbalancer.service;

import com.loadbalancer.loadbalancer.entity.Server;

public interface HashSpaceHandler {

    boolean mapServer(Server server);

    Server findNearestServer(String userName, String email);
}
