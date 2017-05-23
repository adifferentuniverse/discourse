package com.adifferentuniverse.discourse.programme.repository;

import com.adifferentuniverse.discourse.programme.model.Day;
import com.adifferentuniverse.discourse.programme.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
    Day findByConferenceIdAndDate(Long conferenceId, LocalDate date);
}
