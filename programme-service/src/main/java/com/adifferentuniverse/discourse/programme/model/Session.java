package com.adifferentuniverse.discourse.programme.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("SESSION")
@Data
public class Session extends Event {

    private Long sessionTypeId;
    private Long submissionId;
}
