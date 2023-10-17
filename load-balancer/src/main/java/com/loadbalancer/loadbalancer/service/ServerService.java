package com.loadbalancer.loadbalancer.service;

import com.loadbalancer.loadbalancer.entity.Server;

public interface ServerService {
    Server findServer(String... params);

    boolean mapServer(Server server);
}
