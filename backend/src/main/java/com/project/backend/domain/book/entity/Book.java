package com.project.backend.domain.book.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * -- 책 엔티티 --
 *
 * @author -- 정재익 --
 * @since -- 1월 27일 --
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("title")
    private String title;

    @Column(length = 100)
    @JsonProperty("author")
    private String author;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("description")
    private String description;

    @Column(columnDefinition = "TEXT")
    @JsonProperty("image")
    private String image;

    @Column(length = 50, unique = true, nullable = false)
    @JsonProperty("isbn")
    private String isbn;

    private int favoriteCount;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites;

    /**
     * -- 책의 찜 개수를 감소 시키는 메소드 --
     *
     * @return -- boolean --
     * @author -- 정재익 --
     * @since -- 2월 9일 --
     */
    public boolean decreaseFavoriteCount() {
        if (this.favoriteCount > 0) {
            this.favoriteCount--;
        }
        return this.favoriteCount == 0;
    }
}