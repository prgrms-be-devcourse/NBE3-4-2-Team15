package com.project.backend.domain.book.controller;

import com.project.backend.domain.book.dto.BookDTO;
import com.project.backend.domain.book.service.BookService;
import com.project.backend.global.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * -- 도서 컨트롤러 --
 *
 * @author -- 정재익 --
 * @since -- 2월 5일 --
 */
@Tag(name = "BookController", description = "도서 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@SecurityRequirement(name = "bearerAuth")
public class BookController {

    private final BookService bookService;

    /**
     * -- 도서 검색 --
     * api의 정보를 바탕으로 도서를 검색
     * 작가 검색, 제목 검색 기능
     *
     * @param -- query(검색어), searchBy(title = 제목검색, author = 작가검색) --
     * @return -- GenericResponse<List<BookDTO>> --
     * @header -- X-Session-Id (개인별 세션 ID) --
     * @author -- 정재익 --
     * @since -- 2월 5일 --
     */
    @GetMapping
    @Operation(summary = "도서 검색")
    public GenericResponse<List<BookDTO>> searchBooks(@RequestParam(name = "query") String query,
                                                      @RequestParam(name = "searchBy", defaultValue = "title") String searchBy,
                                                      @RequestHeader(name = "X-Session-Id") String sessionId) {
        return GenericResponse.of(bookService.searchBooks(query, searchBy.equalsIgnoreCase("author"), sessionId));
    }

    /**
     * -- 도서 상세 검색 --
     * 책의 상세 정보 조회
     *
     * @param -- isbn --
     * @return -- GenericResponse<BookDTO> --
     * @header -- X-Session-Id (개인별 세션 ID) --
     * @author -- 정재익 --
     * @since -- 2월 5일 --
     */
    @GetMapping("/{isbn}")
    @Operation(summary = "도서 상세 검색")
    public GenericResponse<BookDTO> searchBookDetail(@PathVariable(name = "isbn") String isbn,
                                                     @RequestHeader(name = "X-Session-Id") String sessionId) {
        return GenericResponse.of(bookService.searchBookDetail(isbn, sessionId));
    }

    /**
     * 도서 찜하기,취소하기 기능
     * 로그인한 사용자의 정보를 @AuthenticationPrincipal을 통해 가져와 FavoriteRepository를 이용하여 찜하거나 찜 취소
     * 달라진 도서별 찜 개수를 BookRepository에 반영
     *
     * @param -- FavoriteDTO --
     * @param -- userDetails 로그인한 사용자 정보 --
     * @return -- GenericResponse<String>
     * @author -- 정재익 --
     * @since -- 2월 3일 --
     */
//    @PostMapping("/{id}/favorite")
//    @Operation(summary = "도서 찜 하기")
//    public GenericResponse<String> favoriteBook(@Valid @RequestBody FavoriteDTO favoriteDto, @AuthenticationPrincipal UserDetails userDetails) {
//        return bookService.favoriteBook(favoriteDto, userDetails);
//    }

//    /**
//     * -- 찜한 책 목록을 확인하는 메소드 --
//     * 로그인한 사용자의 정보를 @AuthenticationPrincipal을 통해 가져와 favoriteRepository에서 찜한 책 목록 조회
//     *
//     * @param -- userDetails 로그인한 사용자 정보 --
//     * @return -- GenericResponse<List<BookSimpleDTO>> --
//     * @author -- 정재익 --
//     * @since -- 2월 3일 --
//     */
//    @GetMapping("/favorite")
//    @Operation(summary = "도서 찜 목록")
//    public GenericResponse<List<BookDTO>> searchFavoriteBooks(@AuthenticationPrincipal UserDetails userDetails) {
//        return GenericResponse.of(bookService.searchFavoriteBooks(userDetails));
//    }
}
