package com.loadbalancer.loadbalancer.repository;

import com.loadbalancer.loadbalancer.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

    UserDetails findByUserId(Long userId);
}
