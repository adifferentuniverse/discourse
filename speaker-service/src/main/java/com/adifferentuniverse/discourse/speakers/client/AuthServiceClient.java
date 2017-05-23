package com.adifferentuniverse.discourse.speakers.client;

import org.springframework.cloud.netflix.feign.FeignClient;

//TODO this should be replaced with JWT access
@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    public User findByUsername(String name);
}
