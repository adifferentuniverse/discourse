package com.adifferentuniverse.discourse.programme.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BREAK")
@Data
public class Break extends Event {

    private String name;

}
