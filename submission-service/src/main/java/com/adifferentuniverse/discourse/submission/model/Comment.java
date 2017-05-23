package com.adifferentuniverse.discourse.submission.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long userId;
    private Long submissionId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String value;
}
