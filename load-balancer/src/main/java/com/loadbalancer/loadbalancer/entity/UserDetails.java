package com.loadbalancer.loadbalancer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_requests")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;
}
