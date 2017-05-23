package com.adifferentuniverse.discourse.speakers.repository;

import com.adifferentuniverse.discourse.speakers.model.Speaker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerRepository extends CrudRepository<Speaker, Long> {
    List<Speaker> findByUserId(Long id);
}
