package com.example.admin.demo.repository;

import com.example.admin.demo.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}