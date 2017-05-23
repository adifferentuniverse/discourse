package com.adifferentuniverse.discourse.programme.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance
@DiscriminatorColumn(name="EVENT_TYPE")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long conferenceId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROOM_ID")
    private Room room;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

