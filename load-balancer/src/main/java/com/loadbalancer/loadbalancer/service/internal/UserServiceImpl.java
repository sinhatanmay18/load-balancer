package com.loadbalancer.loadbalancer.service.internal;

import com.loadbalancer.loadbalancer.entity.UserDetails;
import com.loadbalancer.loadbalancer.repository.UserRepository;
import com.loadbalancer.loadbalancer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDetails saveUser(UserDetails userDetails) {
        return this.userRepository.save(userDetails);
    }

    @Override
    public UserDetails getUserById(Long userId) {
        return this.userRepository.findByUserId(userId);
    }
}
