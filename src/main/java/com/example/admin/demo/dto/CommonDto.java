package com.example.admin.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class CommonDto {

  @Getter
  @Setter
  public static class UpdateExposeRequest {

    @NotNull
    private Boolean expose;
  }
}
