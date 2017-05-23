package com.adifferentuniverse.discourse.speakers.service;

import com.adifferentuniverse.discourse.speakers.model.Speaker;
import com.adifferentuniverse.discourse.speakers.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    @Autowired
    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> getByUserId(Long id) {
        return speakerRepository.findByUserId(id);
    }

    public Speaker save(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    public Speaker getById(Long id) {
        return speakerRepository.findOne(id);
    }

    public void delete(Speaker speaker) {
        speakerRepository.delete(speaker);
    }
}
