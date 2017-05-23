package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.clients.AuthServiceClient;
import com.adifferentuniverse.discourse.programme.clients.Conference;
import com.adifferentuniverse.discourse.programme.clients.ConferenceServiceClient;
import com.adifferentuniverse.discourse.programme.clients.User;
import com.adifferentuniverse.discourse.programme.model.Schedule;
import com.adifferentuniverse.discourse.programme.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final AuthServiceClient authServiceClient;
    private final ConferenceServiceClient conferenceServiceClient;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, AuthServiceClient authServiceClient, ConferenceServiceClient conferenceServiceClient) {
        this.scheduleService = scheduleService;
        this.authServiceClient = authServiceClient;
        this.conferenceServiceClient = conferenceServiceClient;
    }

    @GetMapping("/api/v1/schedule")
    public List<Schedule> getSchedulesForCurrentUser(Principal principal) {
        User user = authServiceClient.findByUsername(principal.getName());
        List<Conference> conferences = conferenceServiceClient.findByUserId(user.getId());
        return scheduleService.findByConferenceIds(conferences.stream().map(c -> c.getId()).collect(Collectors.toList()));
    }

    @PostMapping("/api/v1/schedule")
    public Schedule createSchedule(Principal principal, Schedule schedule) {
        checkUserHasPermissions(principal, schedule);
        return scheduleService.save(schedule);
    }

    @GetMapping("/api/v1/schedule/{scheduleId}")
    public Schedule getSchedule(Principal principal, Long scheduleId) {
        return scheduleService.findById(scheduleId);
    }

    @PostMapping("/api/v1/schedule/{scheduleId}")
    public Schedule updateSchedule(Principal principal, Long scheduleId, Schedule schedule) {
        if (scheduleId != schedule.getId()) {
            throw new IllegalStateException();
        }
        checkUserHasPermissions(principal, schedule);
        return scheduleService.save(schedule);
    }

    @DeleteMapping("/api/v1/schedule/{scheduleId}")
    public void deleteSchedule(Principal principal, Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        checkUserHasPermissions(principal, schedule);
        scheduleService.delete(schedule);
    }

    private void checkUserHasPermissions(Principal principal, Schedule schedule) {
        User user = authServiceClient.findByUsername(principal.getName());
        List<Long> admins = conferenceServiceClient.getConferenceAdminsitrators(schedule.getConferenceId());
        if (!admins.contains(user.getId()))
            throw new RuntimeException("User can not perform action");
    }
}
