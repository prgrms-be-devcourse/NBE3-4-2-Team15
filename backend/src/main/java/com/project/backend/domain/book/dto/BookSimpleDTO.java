package com.project.backend.domain.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * -- 간략 보기시 제공되는 DTO --
 *
 * @author -- 정재익 --
 * @since -- 1월 27일 --
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookSimpleDTO {

    private String title;

    private String author;

    private String image;
}