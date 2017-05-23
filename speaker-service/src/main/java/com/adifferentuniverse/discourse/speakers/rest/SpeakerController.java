package com.adifferentuniverse.discourse.speakers.rest;

import com.adifferentuniverse.discourse.speakers.client.AuthServiceClient;
import com.adifferentuniverse.discourse.speakers.client.User;
import com.adifferentuniverse.discourse.speakers.model.Speaker;
import com.adifferentuniverse.discourse.speakers.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
public class SpeakerController {

    private final SpeakerService service;
    private final AuthServiceClient authServiceClient;

    @Autowired
    public SpeakerController(SpeakerService service, AuthServiceClient authServiceClient) {
        this.service = service;
        this.authServiceClient = authServiceClient;
    }

    @GetMapping("/api/v1/speaker")
    public List<Speaker> getSpeakersForCurrentUser(Principal principal) {
        User user = authServiceClient.findByUsername(principal.getName());
        return service.getByUserId(user.getId());
    }

    @PostMapping("/api/v1/speaker")
    public Speaker createSpeaker(Principal principal, Speaker speaker) {
        return service.save(speaker);
    }

    @GetMapping("/api/v1/speaker/{id}")
    public Speaker getSpeakersById(Principal principal, Long id) {
        return service.getById(id);
    }

    @PostMapping("/api/v1/speaker/{id}")
    public Speaker updateSpeaker(Principal principal, Long id, Speaker speaker) {
        if(!Objects.equals(id, speaker.getId()))
            throw new RuntimeException("Speaker does not match path ID value");
        return service.save(speaker);
    }

    @DeleteMapping("/api/v1/speaker/{id}")
    public void deleteSpeaker(Principal principal, Long id) {
        User user = authServiceClient.findByUsername(principal.getName());
        Speaker speaker = service.getById(id);
        if(!Objects.equals(speaker.getUserId(), user.getId()))
            throw new RuntimeException("User can not perform action");
        service.delete(speaker);
    }
}
