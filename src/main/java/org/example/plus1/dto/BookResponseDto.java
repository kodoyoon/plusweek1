package org.example.plus1.dto;

import org.example.plus1.entity.BookEntity;

import java.time.LocalDateTime;

public record BookResponseDto(
    Long id,
    String title,
    String author,
    String content,
    LocalDateTime createdAt
){
  public BookResponseDto(BookEntity saveBook) {
    this(
        saveBook.getId(),
        saveBook.getTitle(),
        saveBook.getAuthor(),
        saveBook.getContents(),
        saveBook.getCreatedAt()
    );
  }
}


