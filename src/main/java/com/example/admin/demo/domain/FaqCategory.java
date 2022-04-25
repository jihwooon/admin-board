package com.example.admin.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FaqCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
