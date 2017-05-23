package com.adifferentuniverse.discourse.programme.repository;

import com.adifferentuniverse.discourse.programme.model.Event;
import com.adifferentuniverse.discourse.programme.model.Schedule;
import com.adifferentuniverse.discourse.programme.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
