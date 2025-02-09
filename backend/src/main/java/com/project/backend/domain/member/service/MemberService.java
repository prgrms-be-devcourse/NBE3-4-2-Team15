package com.project.backend.domain.member.service;

import com.project.backend.domain.member.dto.LoginDto;
import com.project.backend.domain.member.dto.MemberDto;
import com.project.backend.domain.member.dto.MineDto;
import com.project.backend.domain.member.dto.PasswordChangeDto;
import com.project.backend.domain.member.entity.Member;
import com.project.backend.domain.member.exception.MemberException;
import com.project.backend.domain.member.repository.MemberRepository;
import com.project.backend.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.project.backend.domain.member.exception.MemberErrorCode.*;

/**
 *
 * 회원 Service
 *
 * @author 손진영
 * @since 25. 1. 27.
 */
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입 처리
     *
     * @param memberDto 회원가입 요청 DTO
     * @throws MemberException INVALID_PASSWORD: 입력된 두 비밀번호가 일치하지 않는 경우.
     *                         EXISTING_ID: 이미 존재하는 아이디로 회원가입을 시도한 경우.
     * @author 손진영
     * @since 25. 1. 27.
     */
    public void join(MemberDto memberDto) {
        if (!memberDto.getPassword1().equals(memberDto.getPassword2())) {
            throw new MemberException(INVALID_PASSWORD);
        }

        if (memberRepository.findByEmail(memberDto.getEmail()).isPresent()) {
            throw new MemberException(EXISTING_EMAIL);  // 이메일이 존재하는 경우 예외 처리
        }

        if (memberRepository.findByUsername(memberDto.getUsername()).isPresent()) {
            throw new MemberException(EXISTING_USERNAME);
        }
        Member member = Member.builder()
                .username(memberDto.getUsername())
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword1())) // 비밀번호 암호화
                .nickname(memberDto.getNickname())
                .gender(memberDto.getGender())
                .birth(memberDto.getBirth())
                .build();

        memberRepository.save(member);
    }

    /**
     * 내 정보 조회
     *
     * @param username 현재 로그인한 사용자의 아이디
     * @return MemberDto 회원 정보 DTO
     * @throws MemberException NON_EXISTING_ID: 해당 아이디의 화원이 존재하지 않은 경우.
     * @author 손진영
     * @since 25. 1. 27.
     */
    public MemberDto getMyProfile(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberException(NON_EXISTING_USERNAME));
        
        return new MemberDto(member);
    }

    /**
     * 회원 정보 수정
     *
     * @param username 현재 로그인한 사용자의 아이디
     * @param mineDto 변경할 회원 정보 DTO
     * @return MemberDto 수정된 회원 정보 DTO
     * @throws MemberException NON_EXISTING_ID: 해당 아이디의 회원이 존재하지 않은 경우.
     *
     * @author 손진영
     * @since 25. 1. 28.
     */
    @Transactional
    public MemberDto modify(String username, MineDto mineDto) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberException(NON_EXISTING_USERNAME));

        member.setEmail(mineDto.getEmail());
        member.setGender(mineDto.getGender());
        member.setNickname(mineDto.getNickname());
        member.setBirth(mineDto.getBirth());

        return new MemberDto(member);
    }

    /**
     * 회원 탈퇴
     * @param username 현재 로그인한 사용자 아이디
     * @param password 입력된 비밀번호
     * @throws MemberException NON_EXISTING_ID: 해당 아이디의 회원이 존재하지 않는 경우
     *                         INCORRECT_PASSWORD: 입력된 비밀번호가 올바르지 않은 경우
     * @author 이원재
     * @since 25. 2. 6.
     */
    @Transactional
    public void delete(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberException(NON_EXISTING_USERNAME));

        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new MemberException(INCORRECT_PASSWORD);
        }

        memberRepository.delete(member);
    }

    /**
     * 로그인 (JWT 발급)
     * @param loginDto 로그인 요청 DTO(아이디, 비밀번호 포함)
     * @return JWT 토큰 (로그인 성공 시 반환)
     * @throws MemberException NON_EXISTING_ID: 해당 아이디의 회원이 존재하지 않는 경우
     *                         INCORRECT_PASSWORD: 입력된 비밀번호가 올바르지 않은 경우
     * @author 이원재
     * @since 25. 2. 6.
     */
    public String login(LoginDto loginDto) {
        Member member = memberRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(()->new MemberException(NON_EXISTING_USERNAME));

        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) { // 암호화된 비밀번호 비교
            throw new MemberException(INCORRECT_PASSWORD);
        }
        
        return jwtUtil.generateToken(member.getUsername()); // JWT 토큰 발급
    }

    /**
     * 비밀번호 변경
     * @param username 현재 로그인한 사용자의 아이디
     * @param passwordChangeDto 비밀번호 변경 요청 DTO (현재 비밀번호, 새 비밀번호 포함)
     * @throws MemberException NON_EXISTING_ID: 해당 아이디의 회원이 존재하지 않는 경우
     *                         INCORRECT_PASSWORD: 현재 비밀번호가 올바르지 않은 경우
     *                         SAME_AS_OLD_PASSWORD: 새 비밀번호가 현재 비밀번호와 동일한 경우
     * @author 이원재
     * @since 25. 2. 6.
     */
    @Transactional
    public void changePassword(String username, PasswordChangeDto passwordChangeDto) {
        memberRepository.findByUsername(username)
                .map(member -> {
                    if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), member.getPassword())) {
                        throw new MemberException(INCORRECT_PASSWORD);
                    }
                    if (passwordChangeDto.getNewPassword().equals(passwordChangeDto.getCurrentPassword())) {
                        throw new MemberException(SAME_AS_OLD_PASSWORD);
                    }
                    member.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
                    return member;
                })
                .orElseThrow(() -> new MemberException(NON_EXISTING_USERNAME));}
}
