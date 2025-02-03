package com.project.backend.domain.member;

import com.project.backend.domain.member.controller.MemberController;
import com.project.backend.domain.member.entity.Member;
import com.project.backend.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 회원 테스트
 *
 * @author 손진영
 * @since 2025.01.27
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MemberService memberService;

    /**
     * 회원가입 테스트: 성공적인 회원가입을 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("회원가입")
    void t1() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        post("/members")
                                .content("""
                                        {
                                            "username": "test",
                                            "password1": "12345678",
                                            "password2": "12345678",
                                            "nickname": "테스트",
                                            "email": "test@test.com",
                                            "gender" : "0",
                                            "birth" : "2024-10-12"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print());

        Member member = memberService.getMember("test").get();

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(member.getUsername()))
                .andExpect(jsonPath("$.data.nickname").value(member.getNickname()))
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.gender").value(member.getGender()))
                .andExpect(jsonPath("$.data.birth").value(member.getBirth().toString()));
    }

    /**
     * 회원가입 시 중복된 아이디를 사용할 경우, 409 Conflict 상태 코드가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("회원가입, 중복")
    void t2() throws Exception {
        mvc
                .perform(
                        post("/members")
                                .content("""    
                                        {
                                            "username": "user1",
                                            "password1": "12345678",
                                            "password2": "12345678",
                                            "nickname": "유저1",
                                            "email": "user1@users.com",
                                            "gender" : "0",
                                            "birth" : "2024-10-12"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(status().isConflict());
    }

    /**
     * 회원가입 시 입력 값이 유효하지 않으면, 400 Bad Request가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("회원가입, valid")
    void t3() throws Exception {
        mvc
                .perform(
                        post("/members")
                                .content("""    
                                        {
                                            "username": "",
                                            "password1": "",
                                            "password2": "",
                                            "nickname": "",
                                            "email": "",
                                            "gender" : "",
                                            "birth" : ""
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(status().isBadRequest());
    }

    /**
     * 회원가입 시 비밀번호가 일치하지 않으면, 400 Bad Request가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("회원가입, 비밀번호 확인")
    void t4() throws Exception {
        mvc
                .perform(
                        post("/members")
                                .content("""    
                                        {
                                            "username": "test2",
                                            "password1": "123456789",
                                            "password2": "12345678",
                                            "nickname": "테스트",
                                            "email": "test@test.com",
                                            "gender" : "0",
                                            "birth" : "2024-10-12"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(status().isBadRequest());
    }

    /**
     * 회원가입 시 이메일 형식이 유효하지 않으면, 400 Bad Request가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("회원가입, 이메일 형식")
    void t5() throws Exception {
        mvc
                .perform(
                        post("/members")
                                .content("""    
                                        {
                                            "username": "test2",
                                            "password1": "12345678",
                                            "password2": "12345678",
                                            "nickname": "테스트",
                                            "email": "test",
                                            "gender" : "0",
                                            "birth" : "2024-10-12"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(status().isBadRequest());
    }

    /**
     * 로그인 성공 시, 200 OK와 함께 로그인된 회원 정보를 반환하는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("로그인")
    void t6() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        post("/members/login")
                                .content("""
                                        {
                                            "username" : "user1",
                                            "password" : "1234"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        Member member = memberService.getMember("user1").get();

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("login"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(member.getUsername()))
                .andExpect(jsonPath("$.data.nickname").value(member.getNickname()))
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.gender").value(member.getGender()))
                .andExpect(jsonPath("$.data.birth").value(member.getBirth() == null ? null : member.getBirth().toString()));
    }

    /**
     * 존재하지 않는 사용자가 로그인 시, 404 Not Found와 함께 "존재하지 않는 사용자 입니다." 메시지가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("로그인, 없는 사용자")
    void t7() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        post("/members/login")
                                .content("""
                                        {
                                            "username" : "test3",
                                            "password" : "12345678"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("login"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("존재하지 않는 사용자 입니다."));
    }

    /**
     * 비밀번호가 맞지 않는 경우, 401 Unauthorized 상태 코드와 함께 "비밀번호가 맞지 않습니다." 메시지가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.27
     */
    @Test
    @DisplayName("로그인, 비밀번호 맞지 않음")
    void t8() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        post("/members/login")
                                .content("""
                                        {
                                            "username" : "user1",
                                            "password" : "12345678999"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("login"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("비밀번호가 맞지 않습니다."));
    }

    /**
     * 인증된 사용자로 자신의 정보를 조회하는 테스트
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.28
     */
    @Test
    @DisplayName("내 정보 조회")
    void t9() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        get("/members/mine")
                                .header("Authorization", "Bearer " + member.getUsername())
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(member.getUsername()))
                .andExpect(jsonPath("$.data.nickname").value(member.getNickname()))
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.gender").value(member.getGender()))
                .andExpect(jsonPath("$.data.birth").value(member.getBirth() == null ? null : member.getBirth().toString()));
    }

    /**
     * 인증 정보가 없을 때, 401 Unauthorized와 함께 "인증정보가 없습니다." 메시지가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.28
     */
    @Test
    @DisplayName("내 정보 조회, 인증정보 없을 때")
    void t10() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        get("/members/mine")
                                .header("Authorization", "Bearer ")
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("인증정보가 없습니다."));
    }

    /**
     * 인증 정보가 잘못된 경우, 401 Unauthorized와 함께 "인증정보가 올바르지 않습니다." 메시지가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.28
     */
    @Test
    @DisplayName("내 정보 조회, 인증정보로 조회 안됨")
    void t11() throws Exception {

        ResultActions resultActions = mvc
                .perform(
                        get("/members/mine")
                                .header("Authorization", "Bearer test2")
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("인증정보가 올바르지 않습니다."));
    }

    /**
     * 인증된 사용자로 자신의 정보를 수정하는 테스트
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.28
     */
    @Test
    @DisplayName("내 정보 수정")
    void t12() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        put("/members/mine")
                                .header("Authorization", "Bearer " + member.getUsername())
                                .content("""    
                                        {
                                            "password": "12345678",
                                            "nickname": "수정테스트",
                                            "email": "test@naver.com",
                                            "gender" : "1",
                                            "birth" : "1996-08-02"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nickname").value("수정테스트"))
                .andExpect(jsonPath("$.data.email").value("test@naver.com"))
                .andExpect(jsonPath("$.data.gender").value(1))
                .andExpect(jsonPath("$.data.birth").value("1996-08-02"));
    }

    /**
     * 인증된 사용자로 자신의 정보를 수정할 때 비밀번호 값이 없을 때 성공 테스트
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("내 정보 수정, 비밀번호 없을 때")
    void t13() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        put("/members/mine")
                                .header("Authorization", "Bearer " + member.getUsername())
                                .content("""    
                                        {
                                            "password" : "",
                                            "nickname" : "수정테스트",
                                            "email" : "test@naver.com",
                                            "gender" : "1",
                                            "birth" : "1996-08-02"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nickname").value("수정테스트"))
                .andExpect(jsonPath("$.data.email").value("test@naver.com"))
                .andExpect(jsonPath("$.data.gender").value(1))
                .andExpect(jsonPath("$.data.birth").value("1996-08-02"));
    }

    /**
     * 인증 정보가 없는 경우, 401 Unauthorized와 함께 "인증정보가 없습니다." 메시지가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("내 정보 수정, 인증정보 없을 때")
    void t14() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        put("/members/mine")
                                .header("Authorization", "Bearer ")
                                .content("""    
                                        {
                                            "password" : "",
                                            "nickname" : "수정테스트",
                                            "email" : "test@naver.com",
                                            "gender" : "1",
                                            "birth" : "1996-08-02"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("인증정보가 없습니다."));
    }

    /**
     * 인증 정보가 잘못된 경우, 401 Unauthorized와 함께 "인증정보가 올바르지 않습니다." 메시지가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.28
     */
    @Test
    @DisplayName("내 정보 수정, 인증정보로 조회 안됨")
    void t15() throws Exception {

        ResultActions resultActions = mvc
                .perform(
                        put("/members/mine")
                                .header("Authorization", "Bearer test")
                                .content("""    
                                        {
                                            "password" : "",
                                            "nickname" : "수정테스트",
                                            "email" : "test@naver.com",
                                            "gender" : "1",
                                            "birth" : "1996-08-02"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("인증정보가 올바르지 않습니다."));
    }

    /**
     * 내 정보 수정 시 입력 값이 유효하지 않으면, 400 Bad Request가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("내 정보 수정, valid")
    void t16() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        put("/members/mine")
                                .header("Authorization", "Bearer ")
                                .content("""    
                                        {
                                            "password" : "",
                                            "nickname" : "",
                                            "email" : "",
                                            "gender" : "",
                                            "birth" : ""
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("요청이 올바르지 않습니다."));
    }

    /**
     * 회원 탈퇴 테스트
     * 회원 탈퇴 후 회원 조회를 하여 삭제 되었는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("회원 탈퇴")
    void t17() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        delete("/members/mine")
                                .header("Authorization", "Bearer " + member.getUsername())
                                .content("""    
                                        {
                                            "password" : "1234"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("탈퇴 성공"));

        Optional<Member> user1 = memberService.getMember("user1");
        assertTrue(user1.isEmpty());
    }

    /**
     * 회원 탈퇴 시 비밀번호가 올바르지 않다면, 401 코드가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("회원 탈퇴, 비밀번호 맞지 않음")
    void t18() throws Exception {

        Member member = memberService.getMember("user1").get();

        ResultActions resultActions = mvc
                .perform(
                        delete("/members/mine")
                                .header("Authorization", "Bearer " + member.getId())
                                .content("""    
                                        {
                                            "password" : "123444555"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isUnauthorized());
    }

    /**
     * 회원 탈퇴 시 인증정보 없다면, 401 코드가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("회원 탈퇴, 인증정보 없을 때")
    void t19() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        delete("/members/mine")
                                .header("Authorization", "Bearer ")
                                .content("""    
                                        {
                                            "password" : "1234"
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isUnauthorized());
    }

    /**
     * 회원 탈퇴 시 인증정보 없다면, 400 코드가 반환되는지 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.01.31
     */
    @Test
    @DisplayName("회원 탈퇴, Valid")
    void t20() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        delete("/members/mine")
                                .header("Authorization", "Bearer ")
                                .content("""    
                                        {
                                            "password" : ""
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                );

        resultActions
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("mine"))
                .andExpect(status().isBadRequest());
    }

    /**
     * 특정 유저 정보 조회
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.02.03
     */
    @Test
    @DisplayName("특정 유저 정보 조회")
    void t21() throws Exception {
        Member member = memberService.getMember("user1").get();

        mvc
                .perform(get("/members/user1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(member.getUsername()))
                .andExpect(jsonPath("$.data.nickname").value(member.getNickname()))
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.gender").value(member.getGender()))
                .andExpect(jsonPath("$.data.birth").value(member.getBirth() == null ? null : member.getBirth().toString()));
    }

    /**
     * 특정 유저 정보 조회 시
     * 404 에러, "존재하지 않는 사용자 입니다." 메세지 출력 검증
     *
     * @throws Exception
     * @author 손진영
     * @since 2025.02.03
     */
    @Test
    @DisplayName("특정 유저 정보 조회, 없는 username")
    void t22() throws Exception {

        mvc
                .perform(get("/members/non"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("존재하지 않는 사용자 입니다."));
    }
}
