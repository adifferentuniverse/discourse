package com.adifferentuniverse.discourse.programme.service;

import com.adifferentuniverse.discourse.programme.model.Event;
import com.adifferentuniverse.discourse.programme.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return null;
    }

    public Event findById(Long id) {
        return null;
    }

    public void delete(Event event) {

    }
}
