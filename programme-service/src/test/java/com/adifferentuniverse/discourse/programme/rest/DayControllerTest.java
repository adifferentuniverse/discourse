package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.clients.AuthServiceClient;
import com.adifferentuniverse.discourse.programme.clients.ConferenceServiceClient;
import com.adifferentuniverse.discourse.programme.clients.User;
import com.adifferentuniverse.discourse.programme.model.Day;
import com.adifferentuniverse.discourse.programme.model.Schedule;
import com.adifferentuniverse.discourse.programme.repository.DayRepository;
import com.adifferentuniverse.discourse.programme.service.ScheduleService;
import com.adifferentuniverse.discourse.programme.utils.SimplePrincipal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;

import static com.google.common.primitives.Longs.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DayControllerTest {

    @Autowired
    DayController dayController;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    DayRepository dayRepository;

    @MockBean
    AuthServiceClient authServiceClient;

    @MockBean
    ConferenceServiceClient conferenceServiceClient;

    Principal principal = new SimplePrincipal("god@gmail.com");

    @Test
    public void createShouldSaveIfUserAndDayValid() {
        long conferenceId = 1L;

        Schedule schedule = new Schedule();
        schedule.setConferenceId(conferenceId);
        schedule = scheduleService.save(schedule);

        User user = new User();
        user.setId(11L);
        user.setName(principal.getName());
        given(authServiceClient.findByUsername(anyString())).willReturn(user);
        given(conferenceServiceClient.getConferenceAdminsitrators(schedule.getConferenceId())).willReturn(asList
                (user.getId()));

        Day day = new Day();
        day.setConferenceId(conferenceId);
        day.setSchedule(schedule);
        day.setDate(LocalDate.now());

        Day savedDay = dayController.createDay(principal, day);

        assertThat(savedDay.getConferenceId()).isEqualTo(day.getConferenceId());
    }

    @Test(expected = RuntimeException.class)
    public void createShouldThrowErrorIfDayAlreadyExistsForConferenceDay() {
        long conferenceId = 1L;

        Schedule schedule = new Schedule();
        schedule.setConferenceId(conferenceId);
        schedule = scheduleService.save(schedule);

        User user = new User();
        user.setId(11L);
        user.setName(principal.getName());
        given(authServiceClient.findByUsername(anyString())).willReturn(user);
        given(conferenceServiceClient.getConferenceAdminsitrators(schedule.getConferenceId())).willReturn(asList
                (user.getId()));

        Day day = new Day();
        day.setConferenceId(conferenceId);
        day.setSchedule(schedule);
        day.setDate(LocalDate.now());

        dayController.createDay(principal, day);

        Day dayB = new Day();
        dayB.setConferenceId(conferenceId);
        dayB.setSchedule(schedule);
        dayB.setDate(LocalDate.now());

        Day savedDay = dayController.createDay(principal, dayB);
    }

    @Test
    public void createShouldSaveMultipleDifferentConferenceDays() {
        long conferenceIdA = 1L;
        long conferenceIdB = 2L;

        User user = new User();
        user.setId(11L);
        user.setName(principal.getName());
        given(authServiceClient.findByUsername(anyString())).willReturn(user);

        Schedule scheduleA = new Schedule();
        scheduleA.setConferenceId(conferenceIdA);
        scheduleA = scheduleService.save(scheduleA);
        given(conferenceServiceClient.getConferenceAdminsitrators(scheduleA.getConferenceId())).willReturn(asList
                (user.getId()));

        Schedule scheduleB = new Schedule();
        scheduleB.setConferenceId(conferenceIdB);
        scheduleB = scheduleService.save(scheduleB);
        given(conferenceServiceClient.getConferenceAdminsitrators(scheduleB.getConferenceId())).willReturn(asList
                (user.getId()));

        Day dayA = new Day();
        dayA.setConferenceId(conferenceIdA);
        dayA.setSchedule(scheduleA);
        dayA.setDate(LocalDate.now());
        dayController.createDay(principal, dayA);

        Day dayB = new Day();
        dayB.setConferenceId(conferenceIdB);
        dayB.setSchedule(scheduleA);
        dayB.setDate(LocalDate.now());

        Day savedDay = dayController.createDay(principal, dayB);
        assertThat(savedDay.getConferenceId()).isEqualTo(dayB.getConferenceId());
    }
}
