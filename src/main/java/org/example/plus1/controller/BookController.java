package org.example.plus1.controller;


import lombok.RequiredArgsConstructor;
import org.example.plus1.controller.exception.BookNotFoundException;
import org.example.plus1.dto.BookAddRequestDto;
import org.example.plus1.dto.BookResponseDto;
import org.example.plus1.dto.BookUpdateRequestDto;
import org.example.plus1.dto.exception.ErrorResponseDto;
import org.example.plus1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

  private final BookService bookService;
  @PostMapping // 작성
  public ResponseEntity<BookResponseDto> addBook(
      @RequestBody BookAddRequestDto requestDto
  ) {
    BookResponseDto responseDto = bookService.addBook(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @GetMapping ("/{bookId}")// 전체조회
  public ResponseEntity<BookResponseDto> getBook(
      @PathVariable Long bookId
  ) {
    BookResponseDto responseDto = bookService.getBook(bookId);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping //목록조회
  public ResponseEntity<List<BookResponseDto>> getBooks() {
    List<BookResponseDto> responseDto = bookService.getBooks();
    return ResponseEntity.ok(responseDto);
  }

  @PatchMapping("/{bookId}") // 선택한 게시글 수정
  public ResponseEntity<BookResponseDto> updateBook(
      @PathVariable Long bookId,
      @RequestBody BookUpdateRequestDto requestDto
  ){
    BookResponseDto responseDto = bookService.updateBook(bookId,requestDto);
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<Void> deletePost(
      @PathVariable Long bookId,
      @RequestHeader("password") String password
  ){
    bookService.deleteBook(bookId, password);
    return ResponseEntity.noContent().build();
  }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> bookNotFoundExceptionHandler(BookNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new ErrorResponseDto(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage()
        )
    );
    }
}
