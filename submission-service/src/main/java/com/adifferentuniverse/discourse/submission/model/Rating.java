package com.adifferentuniverse.discourse.submission.model;

import lombok.Data;

@Data
public class Rating {

    private Long id;
    private Long userId;
    private Long submissionId;
    private Integer value;
}
