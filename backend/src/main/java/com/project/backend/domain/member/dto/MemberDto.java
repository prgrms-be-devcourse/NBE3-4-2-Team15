package com.project.backend.domain.member.dto;

import com.project.backend.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 *
 * 회원 DTO
 *
 * @author 손진영
 * @since 25. 1. 27.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @NotBlank
    @Length(min = 2, max = 16)
    private String id;

    @NotBlank
    @Length(min = 8)
    private String password1;

    @NotBlank
    private String password2;

    @NotBlank
    @Length(max = 25)
    @Email
    private String email;

    private int gender;

    @NotBlank
    @Length(min = 2, max = 20)
    private String nickname;

    private LocalDate birth;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.gender = member.getGender();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
    }
}