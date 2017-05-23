package com.adifferentuniverse.discourse.programme.rest;

import com.adifferentuniverse.discourse.programme.clients.AuthServiceClient;
import com.adifferentuniverse.discourse.programme.clients.ConferenceServiceClient;
import com.adifferentuniverse.discourse.programme.clients.User;
import com.adifferentuniverse.discourse.programme.model.Schedule;
import com.adifferentuniverse.discourse.programme.repository.ScheduleRepository;
import com.adifferentuniverse.discourse.programme.rest.ScheduleController;
import com.adifferentuniverse.discourse.programme.utils.SimplePrincipal;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;

import static com.google.common.primitives.Longs.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScheduleControllerTest {

    @Autowired
    ScheduleController scheduleController;

    @Autowired
    ScheduleRepository scheduleRepository;

    @MockBean
    AuthServiceClient authServiceClient;

    @MockBean
    ConferenceServiceClient conferenceServiceClient;

    Principal principal = new SimplePrincipal("god@gmail.com");

    @Test(expected = RuntimeException.class)
    public void createShouldThrowErrorIfUserIsNotAllowedToCreateSchedule() {
        assertDatabaseEmpty();
        long conferenceId = 1L;

        Schedule schedule = new Schedule();
        schedule.setConferenceId(conferenceId);

        User user = new User();
        user.setId(11L);
        user.setName(principal.getName());
        given(authServiceClient.findByUsername(anyString())).willReturn(user);
        given(conferenceServiceClient.getConferenceAdminsitrators(schedule.getConferenceId())).willReturn(new ArrayList<>());

        scheduleController.createSchedule(principal, schedule);
    }

    @Test
    public void createShouldSaveIfUserAndScheduleAreValid() {
        assertDatabaseEmpty();
        long conferenceId = 1L;

        Schedule schedule = new Schedule();
        schedule.setConferenceId(conferenceId);

        User user = new User();
        user.setId(11L);
        user.setName(principal.getName());
        given(authServiceClient.findByUsername(anyString())).willReturn(user);
        given(conferenceServiceClient.getConferenceAdminsitrators(schedule.getConferenceId())).willReturn(asList
                (user.getId()));

        Schedule savedSchedule = scheduleController.createSchedule(principal, schedule);

        assertThat(scheduleRepository.findOne(savedSchedule.getId()).getConferenceId()).isEqualTo(conferenceId);
    }

    private void assertDatabaseEmpty() {
        ArrayList<Schedule> schedules = Lists.newArrayList(scheduleRepository.findAll());
        assertThat(schedules).isEmpty();
    }

}
