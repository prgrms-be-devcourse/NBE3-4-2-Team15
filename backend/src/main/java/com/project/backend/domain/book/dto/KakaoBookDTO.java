package com.project.backend.domain.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * -- KakaoBookDTO 클래스 --
 *
 * Kakao API로부터 받아온 도서 정보를 처리하기 위한 DTO
 * 카카오 도서 API의 응답을 매핑
 *
 * @author 김남우
 * @since 2025년 1월 27일
 */
@Getter
@Setter
public class KakaoBookDTO {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String[] authors;

    @NonNull
    private String contents;

    @NonNull
    private String thumbnail;

    @NonNull
    private String isbn;

    @NonNull
    private int favoriteCount;
}