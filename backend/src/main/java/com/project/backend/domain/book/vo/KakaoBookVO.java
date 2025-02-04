package com.project.backend.domain.book.vo;

import com.project.backend.domain.book.dto.KakaoBookDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * -- KakaoBookVO 객체는 카카오 도서 API에서 받은 응답 데이터를 표현하는 VO --
 * 카카오 API의 응답으로 도서 목록이 포함된 documents 필드를 포함
 *
 * @author 김남우
 * @since 2025년 1월 27일
 */
@Getter
@Setter
public class KakaoBookVO {
    private List<KakaoBookDTO> documents;
}