package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.clients.AuthServiceClient;
import com.adifferentuniverse.discourse.programme.clients.ConferenceServiceClient;
import com.adifferentuniverse.discourse.programme.clients.User;
import com.adifferentuniverse.discourse.programme.model.Day;
import com.adifferentuniverse.discourse.programme.model.Event;
import com.adifferentuniverse.discourse.programme.service.DayService;
import com.adifferentuniverse.discourse.programme.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;
    private final AuthServiceClient authServiceClient;
    private final ConferenceServiceClient conferenceServiceClient;

    @Autowired
    public EventController(EventService eventService, AuthServiceClient authServiceClient, ConferenceServiceClient conferenceServiceClient) {
        this.eventService = eventService;
        this.authServiceClient = authServiceClient;
        this.conferenceServiceClient = conferenceServiceClient;
    }

    @PostMapping("/api/v1/event")
    public Event createEvent(Principal principal, Event event) {
        checkUserHasPermissions(principal, event);
        return eventService.save(event);
    }

    @GetMapping("/api/v1/event/{id}")
    public Event findEventById(Principal principal, Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/api/v1/event/{id}")
    public Event updateEvent(Principal principal, Long id, Event event) {
        if(id != event.getId())
            throw new IllegalStateException();
        checkUserHasPermissions(principal, event);
        return eventService.save(event);
    }

    @DeleteMapping("/api/v1/event/{id}")
    public void deleteEvent(Principal principal, Long id) {
        Event event = eventService.findById(id);
        checkUserHasPermissions(principal, event);
        eventService.delete(event);
    }

    private void checkUserHasPermissions(Principal principal, Event event) {
        User user = authServiceClient.findByUsername(principal.getName());
        List<Long> admins = conferenceServiceClient.getConferenceAdminsitrators(event.getConferenceId());
        if (!admins.contains(user.getId()))
            throw new RuntimeException("User can not perform action");
    }
}
