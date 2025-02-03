package com.project.backend.domain.review.comment.service;


import com.project.backend.domain.member.entity.Member;
import com.project.backend.domain.member.repository.MemberRepository;
import com.project.backend.domain.review.comment.dto.ReviewCommentDto;
import com.project.backend.domain.review.comment.entity.ReviewComment;
import com.project.backend.domain.review.comment.repository.ReviewCommentRepository;
import com.project.backend.domain.review.exception.ReviewErrorCode;
import com.project.backend.domain.review.exception.ReviewException;
import com.project.backend.domain.review.review.entity.Review;
import com.project.backend.domain.review.review.repository.ReviewRepository;
import com.project.backend.domain.review.review.reviewDTO.ReviewsDTO;
import com.project.backend.domain.review.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 리뷰 Service
 *
 * @author shjung
 * @since 25. 1. 24.
 */
@Service
@RequiredArgsConstructor
public class ReviewCommentService {

    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    /**
     * 리뷰 코멘트 목록 출력
     * @param --  reviewId -- 리뷰 id
     * @return ReviewCommentDtoList
     * @return  List<ReviewCommentDto> - id,reviewId, userId, comment, recommendCount
     *
     * @author -- 이광석
     * @since -- 25.01.17
     */
    public List<ReviewCommentDto> findByReview(Integer reviewId) {
        return reviewCommentRepository.findAllByReviewId(reviewId).stream()
                .map(comment->ReviewCommentDto.builder()
                        .id(comment.getId())
                        .reviewId(comment.getReview().getId())
                        .userId(comment.getUserId())
                        .comment(comment.getComment())
                        .recommendCount(comment.getRecommend().size())
                        .build()
                ).collect(Collectors.toList());
    }

    /**
     * 댓글 생성
     * @param reviewId
     * @param reviewCommentDto
     * @return ReviewCommentDto - id,commend, userId,
     *
     * @author -- 이광석
     * @since -- 25.01.17
     */
    public ReviewCommentDto write(Integer reviewId, ReviewCommentDto reviewCommentDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewException(
                        ReviewErrorCode.REVIEW_NOT_FOUND.getStatus(),
                        ReviewErrorCode.REVIEW_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.REVIEW_NOT_FOUND.getMessage()
                ));


        ReviewComment reviewComment= reviewCommentRepository.save(ReviewComment.builder()
                        .review(review)
                        .userId(reviewCommentDto.getUserId())
                        .comment(reviewCommentDto.getComment())
                        .recommend(new ArrayList<>())
                        .build());
        return ReviewCommentDto.builder()
                .id(reviewComment.getId())
                .comment(reviewComment.getComment())
                .userId(reviewComment.getUserId())
                .build();
    }

    /**
     * 댓글 수정
     * @param reviewId
     * @param commentId
     * @param reviewCommentDto
     * @return ReviewCommentDto - id, comment, userId
     *
     * @author -- 이광석
     * @since -- 25.01.17
     */
    public ReviewCommentDto modify(Integer reviewId, Integer commentId,ReviewCommentDto reviewCommentDto) {


        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                        ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                ));
        reviewComment.setComment(reviewCommentDto.getComment());


        reviewCommentRepository.save(reviewComment);
        return ReviewCommentDto.builder()
                .id(reviewComment.getId())
                .comment(reviewComment.getComment())
                .userId(reviewComment.getUserId())
                .build();
    }

    /**
     * 댓글 삭제
     * @param commentId
     * @return RevieCommendDto - id,commend, userId
     *
     * @author -- 이광석
     * @since -- 25.01.17
     */
    public ReviewCommentDto delete(Integer commentId) {
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                        ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                ));
        reviewCommentRepository.delete(reviewComment);
        return ReviewCommentDto.builder()
                .id(reviewComment.getId())
                .comment(reviewComment.getComment())
                .userId(reviewComment.getUserId())
                .build();
    }

    /**
     * 댓글 추천
     * @param commentId
     * @param memberId
     * @return Boolean - 추천(true)/추천 취소(false)
     *
     * @author -- 이광석
     * @since -- 25.01.17
     */
    public Boolean recommend(Integer commentId,Long memberId) {
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                        ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                ));
        Member member = memberRepository.findById(memberId)
                        .orElseThrow(()->new ReviewException(
                                ReviewErrorCode.MEMBER_NOT_FOUND.getStatus(),
                                ReviewErrorCode.MEMBER_NOT_FOUND.getErrorCode(),
                                ReviewErrorCode.MEMBER_NOT_FOUND.getMessage()
                        ));

        List<Member> members  = reviewComment.getRecommend();
        if(members.contains(member)){
            members.remove(member);
            reviewComment.setRecommend(members);

            reviewCommentRepository.save(reviewComment);
            return false;
        }else{
            members.add(member);
            reviewComment.setRecommend(members);
            reviewCommentRepository.save(reviewComment);
            return true;
        }

    }

    /**
     *
     * @param commentId
     * @return ReviewCommentDto - id, comment, userId
     *
     * @author 이광석
     * @since 25.02.03
     */
    public ReviewCommentDto findById(Integer commentId) {
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
        ));
        return ReviewCommentDto.builder()
                .id(reviewComment.getId())
                .comment(reviewComment.getComment())
                .userId(reviewComment.getUserId())
                .build();
    }
}
