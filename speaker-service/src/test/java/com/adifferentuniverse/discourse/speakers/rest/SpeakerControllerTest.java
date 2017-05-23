package com.adifferentuniverse.discourse.speakers.rest;

import com.adifferentuniverse.discourse.speakers.client.AuthServiceClient;
import com.adifferentuniverse.discourse.speakers.model.Speaker;
import com.adifferentuniverse.discourse.speakers.utils.SimplePrincipal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpeakerControllerTest {

    @MockBean
    AuthServiceClient authServiceClient;

    @Autowired
    SpeakerController controller;

    Principal principal = new SimplePrincipal("gpd@gmail.com");

    @Test
    public void createShouldSaveSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setUserId(1L);

        Speaker savedSpeaker = controller.createSpeaker(principal, speaker);

        assertThat(savedSpeaker.getUserId()).isEqualTo(speaker.getUserId());
    }
}
