package org.example.plus1.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.plus1.controller.exception.AuthorizeException;
import org.example.plus1.controller.exception.BookNotFoundException;
import org.example.plus1.dto.BookAddRequestDto;
import org.example.plus1.dto.BookResponseDto;
import org.example.plus1.dto.BookUpdateRequestDto;
import org.example.plus1.entity.BookEntity;
import org.example.plus1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookRepository bookRepository;

  public BookResponseDto addBook(BookAddRequestDto requestDto) {
    BookEntity bookEntity = new BookEntity(requestDto);
    BookEntity saveBook = bookRepository.save(bookEntity);
    return new BookResponseDto(saveBook);
  }

  public BookResponseDto getBook(Long bookId) {
    BookEntity bookEntity = getBookEntity(bookId);
    return new BookResponseDto(bookEntity);
  }

  public List<BookResponseDto> getBooks(){
    return bookRepository.findAllByOrderByCreatedAtDesc().stream()
        .map(BookResponseDto::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public BookResponseDto updateBook(Long bookId, BookUpdateRequestDto requestDto) {
    BookEntity bookEntity = getBookEntity(bookId);
    verifyPassword(bookEntity, requestDto.getPassword());
    bookEntity.update(requestDto);
    return new BookResponseDto(bookEntity);
  }
  public void deleteBook(Long bookId, String password) {
    BookEntity bookEntity = getBookEntity(bookId);
    verifyPassword(bookEntity, password);
    bookRepository.delete(bookEntity);
  }

  private BookEntity getBookEntity(Long bookId) { //공통되니까
    return bookRepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException("해당 게시글을 찾을 수 없습니다."));
  }


    private static void verifyPassword(BookEntity bookEntity, String password) {
    if(!bookEntity.passwordMatches(password)) {
      throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
    }
    }

}
