package com.adifferentuniverse.discourse.programme.service;

import com.adifferentuniverse.discourse.programme.model.Session;
import com.adifferentuniverse.discourse.programme.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public Session findById(Long id) {
        return sessionRepository.findOne(id);
    }

    public void delete(Session session) {
        sessionRepository.delete(session);
    }
}
