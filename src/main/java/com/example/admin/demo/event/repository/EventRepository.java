package com.example.admin.demo.event.repository;

import com.example.admin.demo.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

  Optional<Event> findByEventIdAndEnableIsTrue(Long eventId);

  List<Event> findByEventIdInAndEnableIsTrue(List<Long> eventsId);

}
