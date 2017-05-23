package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.clients.AuthServiceClient;
import com.adifferentuniverse.discourse.programme.clients.ConferenceServiceClient;
import com.adifferentuniverse.discourse.programme.clients.User;
import com.adifferentuniverse.discourse.programme.model.Session;
import com.adifferentuniverse.discourse.programme.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class SessionController {

    private final SessionService sessionService;
    private final AuthServiceClient authServiceClient;
    private final ConferenceServiceClient conferenceServiceClient;

    @Autowired
    public SessionController(SessionService sessionService, AuthServiceClient authServiceClient, ConferenceServiceClient conferenceServiceClient) {
        this.sessionService = sessionService;
        this.authServiceClient = authServiceClient;
        this.conferenceServiceClient = conferenceServiceClient;
    }

    @PostMapping("/api/v1/session")
    public Session createSession(Principal principal, Session session) {
        checkUserHasPermissions(principal, session);
        return sessionService.save(session);
    }

    @GetMapping("/api/v1/session/{id}")
    public Session findSessionById(Principal principal, Long id) {
        return sessionService.findById(id);
    }

    @PostMapping("/api/v1/session/{id}")
    public Session updateSession(Principal principal, Long id, Session session) {
        if(id != session.getId())
            throw new IllegalStateException();
        checkUserHasPermissions(principal, session);
        return sessionService.save(session);
    }

    @DeleteMapping("/api/v1/session/{id}")
    public void deleteSession(Principal principal, Long id) {
        Session session = sessionService.findById(id);
        checkUserHasPermissions(principal, session);
        sessionService.delete(session);
    }

    private void checkUserHasPermissions(Principal principal, Session session) {
        User user = authServiceClient.findByUsername(principal.getName());
        List<Long> admins = conferenceServiceClient.getConferenceAdminsitrators(session.getConferenceId());
        if (!admins.contains(user.getId()))
            throw new RuntimeException("User can not perform action");
    }
}
