package org.example.plus1.dto;

import lombok.Getter;

@Getter
public class BookUpdateRequestDto {
  private String title;
  private String author;
  private String content;
  private String password;
}
