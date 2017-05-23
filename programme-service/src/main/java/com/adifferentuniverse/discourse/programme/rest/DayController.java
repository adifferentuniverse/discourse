package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.clients.AuthServiceClient;
import com.adifferentuniverse.discourse.programme.clients.ConferenceServiceClient;
import com.adifferentuniverse.discourse.programme.clients.User;
import com.adifferentuniverse.discourse.programme.model.Day;
import com.adifferentuniverse.discourse.programme.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class DayController {

    private final DayService dayService;
    private final AuthServiceClient authServiceClient;
    private final ConferenceServiceClient conferenceServiceClient;

    @Autowired
    public DayController(DayService dayService, AuthServiceClient authServiceClient, ConferenceServiceClient conferenceServiceClient) {
        this.dayService = dayService;
        this.authServiceClient = authServiceClient;
        this.conferenceServiceClient = conferenceServiceClient;
    }

    @PostMapping("/api/v1/day")
    public Day createDay(Principal principal, Day day) {
        checkUserHasPermissions(principal, day);
        return dayService.save(day);
    }

    @GetMapping("/api/v1/day/{id}")
    public Day findDayById(Principal principal, Long id) {
        return dayService.findById(id);
    }

    @PostMapping("/api/v1/day/{id}")
    public Day updateDay(Principal principal, Long id, Day day) {
        if(id != day.getId())
            throw new IllegalStateException();
        checkUserHasPermissions(principal, day);
        return dayService.save(day);
    }

    @DeleteMapping("/api/v1/day/{id}")
    public void deleteDay(Principal principal, Long id) {
        Day day = dayService.findById(id);
        checkUserHasPermissions(principal, day);
        dayService.delete(day);
    }

    private void checkUserHasPermissions(Principal principal, Day day) {
        User user = authServiceClient.findByUsername(principal.getName());
        List<Long> admins = conferenceServiceClient.getConferenceAdminsitrators(day.getConferenceId());
        if (!admins.contains(user.getId()))
            throw new RuntimeException("User can not perform action");
    }
}
