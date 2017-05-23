package com.adifferentuniverse.discourse.programme.repository;

import com.adifferentuniverse.discourse.programme.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    Schedule findByConferenceId(Long conferenceId);
}
