package com.example.admin.demo.repository;

import com.example.admin.demo.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

  Optional<Event> findByEventIdAndEnableIsTrue(Long eventId);

  List<Event> findByEventIdInAndEnableIsTrue(List<Long> eventsId);

  Page<Event> findAllByEventTitleContaining(Pageable pageable, String eventTitle);

//  Page<Event> findAll(Specification<Event> spec, Pageable pageable);

}
