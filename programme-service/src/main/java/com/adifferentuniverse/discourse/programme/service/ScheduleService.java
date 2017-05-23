package com.adifferentuniverse.discourse.programme.service;

import com.adifferentuniverse.discourse.programme.model.Schedule;
import com.adifferentuniverse.discourse.programme.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(Long scheduleId) {
        return scheduleRepository.findOne(scheduleId);
    }

    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    public Schedule findByConferenceId(Long conferenceId) {
        return scheduleRepository.findByConferenceId(conferenceId);
    }

    public List<Schedule> findByConferenceIds(List<Long> conferenceIds) {
        return conferenceIds.stream()
                .map(scheduleRepository::findByConferenceId)
                .collect(Collectors.toList());
    }
}
