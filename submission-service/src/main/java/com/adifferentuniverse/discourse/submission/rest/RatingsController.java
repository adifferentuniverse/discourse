package com.adifferentuniverse.discourse.submission.rest;

import com.adifferentuniverse.discourse.submission.model.Rating;
import com.adifferentuniverse.discourse.submission.model.Ratings;
import com.adifferentuniverse.discourse.submission.model.Submission;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class RatingsController {

    public Rating createRating(Principal principal, Rating rating) {
        return null;
    }

    public Rating updateRating(Principal principal, Rating rating) {
        return null;
    }

    public Rating deleteRating(Principal principal, Rating rating) {
        return null;
    }

    public Ratings getRatingsForSubmission(Principal principal, Long submissionId) {
        return null;
    }
}
