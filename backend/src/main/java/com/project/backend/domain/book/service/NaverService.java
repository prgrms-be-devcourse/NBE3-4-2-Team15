package com.project.backend.domain.book.service;

import com.project.backend.domain.book.dto.BookDTO;
import com.project.backend.domain.book.dto.BookSimpleDTO;
import com.project.backend.domain.book.exception.BookErrorCode;
import com.project.backend.domain.book.exception.BookException;
import com.project.backend.domain.book.vo.NaverBookVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


/**
 * -- 네이버 도서 작업을 처리하는 서비스클래스 --
 *
 * @author -- 정재익 --
 * @since -- 2월 4일 --
 */
@RequiredArgsConstructor
@Service
public class NaverService {

    private final ModelMapper modelMapper;
    private final BookService bookService;

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    @Value("${naver.book-search-url}")
    private String apiUrl;

    /**
     * -- 네이버api를 통해 검색어에 대한 도서데이터를 가져오는 메소드 --
     * 책은 한번에 30권 조회되도록 설정했다.
     * <p>
     *
     * @param -- title (컨트롤러에서 입력한 검색어) --
     * @return -- List<NaverBookVO.Item> --
     * @author -- 정재익 --
     * @since -- 2월 3일 --
     */
    private List<NaverBookVO.Item> BookDataFromNaverApi(String title) {
        if (title == null || title.isEmpty()) {
            throw new BookException(BookErrorCode.BOOK_NOT_FOUND);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        String url = apiUrl + "?query=" + title + "&display=30";
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<NaverBookVO> response = restTemplate.exchange(url, HttpMethod.GET, entity, NaverBookVO.class);

        return Optional.ofNullable(response.getBody())
                .map(NaverBookVO::getItems)
                .orElseThrow(() -> new BookException(BookErrorCode.BOOK_NOT_FOUND));
    }

    /**
     * -- 네이버api를 통해 받아온 검색 결과를 List<BookSimpleDto>로 변환하여 컨트롤러에 반환하는 메소드 --
     * -- 검색결과를 List<BookDto>로 만들어서 saveBooks 메소드에 전달
     * <p>
     *
     * @param -- title (컨트롤러에서 입력한 검색어) --
     * @return -- List<BookSimpleDTO> --
     * @author -- 정재익 --
     * @since -- 2월 4일 --
     */
    public List<BookSimpleDTO> searchTitleBooks(String title) {
        List<NaverBookVO.Item> items = BookDataFromNaverApi(title);
        bookService.saveBooks(items.stream().map(item -> modelMapper.map(item, BookDTO.class)).toList());
        return items.stream().map(item -> modelMapper.map(item, BookSimpleDTO.class)).toList();
    }
}
