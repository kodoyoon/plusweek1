package org.example.plus1.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.plus1.dto.BookAddRequestDto;
import org.example.plus1.dto.BookUpdateRequestDto;

@Getter
@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity extends TimeEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 20)
  private String title;
  @Column(nullable = false, length = 15)
  private String author;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false, length = 500)
  private String contents;

  public BookEntity(BookAddRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.author = requestDto.getAuthor();
    this.password = requestDto.getPassword();
    this.contents = requestDto.getContent();
  }
  public void update(BookUpdateRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.author = requestDto.getAuthor();
    this.contents = requestDto.getContent();
  }
  public boolean passwordMatches(String inputPassword) {
    return this.password.equals(inputPassword);
  }
}
