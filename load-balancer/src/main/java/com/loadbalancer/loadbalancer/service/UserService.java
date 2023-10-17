package com.loadbalancer.loadbalancer.service;

import com.loadbalancer.loadbalancer.entity.UserDetails;

public interface UserService {

    UserDetails saveUser(UserDetails userDetails);

    UserDetails getUserById(Long userId);
}
