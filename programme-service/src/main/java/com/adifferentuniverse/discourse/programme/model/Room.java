package com.adifferentuniverse.discourse.programme.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer capacity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DAY_ID")
    private Day day;

    @OneToMany(mappedBy = "room")
    private List<Event> sessions = new ArrayList<>();
}
