package com.adifferentuniverse.discourse.conferences.rest;

import java.time.LocalDateTime;
import java.util.List;

public class Conference {
    private Long id;
    private String name;
    List<SessionType> sessionTypes;
    LocalDateTime conferenceStart;
    LocalDateTime conferenceEnd;
}
