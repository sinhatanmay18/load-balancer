package com.loadbalancer.loadbalancer.repository;

import com.loadbalancer.loadbalancer.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
