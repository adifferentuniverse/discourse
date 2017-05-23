package com.adifferentuniverse.discourse.conferences.rest;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class ConferenceController {

    public List<Conference> listAllConferences(Principal principal) {
        return null;

    }
}
