package com.example.admin.demo.event.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(EventController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class AbstractIntegrationTest {
}
