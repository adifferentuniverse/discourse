package com.adifferentuniverse.discourse.programme.service;

import com.adifferentuniverse.discourse.programme.model.Day;
import com.adifferentuniverse.discourse.programme.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional
public class DayService {

    private final DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public Day save(Day day) {
        Day date = dayRepository.findByConferenceIdAndDate(day.getConferenceId(), day.getDate());
        if(date != null && !Objects.equals(date.getId(), day.getId())) {
            throw new RuntimeException("Day already exists");
        }
        return dayRepository.save(day);
    }

    public Day findById(Long id) {
        return dayRepository.findOne(id);
    }

    public void delete(Day day) {
        dayRepository.delete(day);
    }

    public Day findByConferenceIdAndDate(Long conferenceId, LocalDate date) {
        return dayRepository.findByConferenceIdAndDate(conferenceId, date);
    }
}
