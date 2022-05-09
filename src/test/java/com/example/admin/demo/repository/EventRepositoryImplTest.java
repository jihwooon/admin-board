package com.example.admin.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventRepositoryImplTest {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventRepositoryImpl eventRepositoryImpl;

}
