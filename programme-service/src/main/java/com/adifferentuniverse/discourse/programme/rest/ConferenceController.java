package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.model.Day;
import com.adifferentuniverse.discourse.programme.model.Schedule;
import com.adifferentuniverse.discourse.programme.model.Session;
import com.adifferentuniverse.discourse.programme.service.DayService;
import com.adifferentuniverse.discourse.programme.service.ScheduleService;
import com.adifferentuniverse.discourse.programme.service.SessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;

@RestController
public class ConferenceController {

    private final ScheduleService scheduleService;
    private final DayService dayService;
    private final SessionService sessionService;

    public ConferenceController(ScheduleService scheduleService, DayService dayService, SessionService sessionService) {
        this.scheduleService = scheduleService;
        this.dayService = dayService;
        this.sessionService = sessionService;
    }

    @GetMapping("/api/v1/conference/{conferenceId}/schedule")
    public Schedule getScheduleForConferenceId(Principal principal, Long conferenceId) {
        return scheduleService.findByConferenceId(conferenceId);
    }

    @GetMapping("/api/v1/conference/{conferenceId}/schedule/{year}/{month}/{day}")
    public Day getDayForConferenceId(Principal principal, Long conferenceId, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return dayService.findByConferenceIdAndDate(conferenceId, date);
    }

    @GetMapping("/api/v1/conference/{conferenceId}/schedule/{year}/{month}/{day}/{sessionId}")
    public Session getSessionForDayAndConferenceId(Principal principal, Long conferenceId, int year, int month, int day,
                                                   Long sessionId) {
        return sessionService.findById(sessionId);
    }
}
