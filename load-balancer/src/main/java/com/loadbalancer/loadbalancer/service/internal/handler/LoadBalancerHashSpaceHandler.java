package com.loadbalancer.loadbalancer.service.internal.handler;

import com.loadbalancer.loadbalancer.entity.Server;
import com.loadbalancer.loadbalancer.service.HashSpaceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

@Component("loadBalancerHashSpaceHandler")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoadBalancerHashSpaceHandler implements HashSpaceHandler {

    private final TreeMap<Long, Server> circle = new TreeMap<>();
    private final Long SIZE = Long.MAX_VALUE;
    @Override
    public boolean mapServer(Server server) {
        String encryptedServerDetail = this.encryptString(server.getIp() + server.getPort()).substring(0,15);
        Long serverKeyIndex = Long.parseLong(encryptedServerDetail, 16);
        try {
            this.circle.put(serverKeyIndex, server);
        }
        catch (Exception exception){
            log.error("Hash Space already full");
            return false;
        }
        return true;
    }
    @Override
    public Server findNearestServer(String userName, String email) {
        String encryptedUserDetail = this.encryptString(email + userName).substring(0,15);
        Long objectKeyIndex = Long.parseLong(encryptedUserDetail, 16);
        return this.findServer(objectKeyIndex);
    }

    private Server findServer(Long objectKeyIndex){

        if(this.circle.get(objectKeyIndex) != null){
            return this.circle.get(objectKeyIndex);
        }
        Long clockwiseIndex = this.circle.ceilingKey(objectKeyIndex);
        Long counterClockWiseIndex = this.circle.floorKey(objectKeyIndex);

        if(clockwiseIndex != null){
            return this.circle.get(clockwiseIndex);
        }

        return this.circle.get(counterClockWiseIndex);
    }

    private String encryptString(String encryptionInput) {
        return sha256(encryptionInput);
    }

    private String sha256(String originalString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
