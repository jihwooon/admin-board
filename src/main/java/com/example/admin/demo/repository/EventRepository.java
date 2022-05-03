package com.example.admin.demo.repository;

import com.example.admin.demo.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

  Optional<Event> findByEventIdAndEnableIsTrue(Long eventId);

  List<Event> findByEventIdInAndEnableIsTrue(List<Long> eventsId);
}
