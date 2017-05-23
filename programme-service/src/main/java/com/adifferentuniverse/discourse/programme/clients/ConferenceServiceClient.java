package com.adifferentuniverse.discourse.programme.clients;


import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

@FeignClient(name = "conference-service")
public interface ConferenceServiceClient {
    public List<Conference> findByUserId(Long id);

    List<Long> getConferenceAdminsitrators(Long conferenceId);
}
