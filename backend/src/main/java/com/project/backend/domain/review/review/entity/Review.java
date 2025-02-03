package com.project.backend.domain.review.review.entity;


import com.project.backend.domain.member.entity.Member;
import com.project.backend.domain.review.comment.entity.ReviewComment;
import com.project.backend.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;



/**
 * 리뷰 dto
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA가 사용할 수 있도록 기본 생성자 추가
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bookId;

    private String memberId;

    private String content;

    private Integer rating;

    @OneToMany(mappedBy = "review",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<ReviewComment> comments;

    @ManyToMany
    private List<Member> recommendMember;

    /**
     * 상속 받은 CreateAt 초기화
     */

}
