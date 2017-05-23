package com.adifferentuniverse.discourse.speakers.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Speaker {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String name;
    private String email;
    private String bio;
    private String twitterHandle;
    private String avatarUrl;
    private String company;
    private String blog;
    private String qualifications;
}
