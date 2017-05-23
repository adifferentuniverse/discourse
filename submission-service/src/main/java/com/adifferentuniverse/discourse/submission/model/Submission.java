package com.adifferentuniverse.discourse.submission.model;

import lombok.Data;

import java.util.List;

@Data
public class Submission {

    private Long id;
    private Long userId;
    private Long conferenceId;
    private String title;
    private String summary;
    private Long sessionTypeId;
    private Long trackId;
    private List<Long> speakerIds;
    private Long languageId;
    private Long audienceLevelId;
    private boolean sponsorSubmission;
//    state: ProposalState,
//    demoLevel: Option[String],
//    userGroup: Option[Boolean],
//    wishlisted: Option[Boolean] = None
}
