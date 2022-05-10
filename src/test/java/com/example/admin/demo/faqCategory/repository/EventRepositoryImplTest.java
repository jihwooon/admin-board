package com.example.admin.demo.faqCategory.repository;

import com.example.admin.demo.event.repository.EventRepository;
import com.example.admin.demo.event.repository.impl.EventRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventRepositoryImplTest {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventRepositoryImpl eventRepositoryImpl;

}
