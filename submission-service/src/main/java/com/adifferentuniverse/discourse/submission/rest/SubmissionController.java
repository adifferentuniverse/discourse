package com.adifferentuniverse.discourse.submission.rest;

import com.adifferentuniverse.discourse.submission.model.Submission;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class SubmissionController {

    public Submission createSubmission(Principal principal, Submission submission) {
        return null;
    }

    public Submission updateSubmission(Principal principal, Submission submission) {
        return null;
    }

    public Map<Submission, Float> getSubmissionsForConferenceGroupedByAverageRating(Principal principal, Long conferenceId) {
        return null;
    }
}
