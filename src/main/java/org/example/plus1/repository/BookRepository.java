package org.example.plus1.repository;

import org.example.plus1.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
 List<BookEntity> findAllByOrderByCreatedAtDesc();
}
