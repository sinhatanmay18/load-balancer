package com.loadbalancer.loadbalancer.service.internal;

import com.loadbalancer.loadbalancer.entity.Server;
import com.loadbalancer.loadbalancer.service.HashSpaceHandler;
import com.loadbalancer.loadbalancer.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServerServiceImpl implements ServerService {
    @Qualifier("loadBalancerHashSpaceHandler")
    private final HashSpaceHandler hashSpaceHandler;
    @Override
    public Server findServer(String... params) {
        String userName = params[0];
        String email = params[1];
        return  this.hashSpaceHandler.findNearestServer(userName, email);
    }

    @Override
    public boolean mapServer(Server server) {
        return this.hashSpaceHandler.mapServer(server);
    }
}
