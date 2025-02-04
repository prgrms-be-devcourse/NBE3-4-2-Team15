package com.project.backend.domain.book.repository;

import com.project.backend.domain.book.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * -- 책 저장소 --
 *
 * @author -- 정재익, 김남우 --
 * @since -- 1월 27일 --
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);

    List<Book> findAll(Sort sort);

    List<Book> findByAuthor(String author);
}