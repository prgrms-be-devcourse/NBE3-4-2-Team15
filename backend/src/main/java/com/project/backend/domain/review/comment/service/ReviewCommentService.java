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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public List<ReviewCommentDto> findComment(Long reviewId) {
        ReviewsDTO reviewsDTO = reviewService.findById(reviewId);
        List<ReviewCommentDto> comments= reviewsDTO.getReviewCommentDtos().stream()
                .filter(comment->comment.getParentId()==null)
                .collect(Collectors.toList());
        return comments;
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
    public ReviewCommentDto write(Long reviewId, ReviewCommentDto reviewCommentDto) {
        System.out.println(1);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewException(
                        ReviewErrorCode.REVIEW_NOT_FOUND.getStatus(),
                        ReviewErrorCode.REVIEW_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.REVIEW_NOT_FOUND.getMessage()
                ));
        ReviewComment reviewComment;
        //자식 댓글일때
        if(reviewCommentDto.getParentId()!=null) {
            System.out.println(2);
            ReviewComment  parentsComment = reviewCommentRepository.findById(reviewCommentDto.getParentId())
                    .orElseThrow(() -> new ReviewException(
                            ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                            ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                            ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                    ));
            if(parentsComment.getDepth()+1>=2){
               throw new ReviewException(
                       ReviewErrorCode.INVALID_COMMENT_DEPTH.getStatus(),
                       ReviewErrorCode.INVALID_COMMENT_DEPTH.getErrorCode(),
                       ReviewErrorCode.INVALID_COMMENT_DEPTH.getMessage()
               );
            }
             reviewComment= reviewCommentRepository.save(ReviewComment.builder()
                    .review(review)
                    .userId(reviewCommentDto.getUserId())
                    .comment(reviewCommentDto.getComment())
                    .recommend(new HashSet<>())
                    .parent(parentsComment)
                     .depth(parentsComment.getDepth()+1)
                    .build());

        }else{ // 부모댓글 일때
            System.out.println(3);
             reviewComment = reviewCommentRepository.save(ReviewComment.builder()
                    .review(review)
                    .userId(reviewCommentDto.getUserId())
                    .comment(reviewCommentDto.getComment())
                    .recommend(new HashSet<>())
                             .depth(0)
                    .build());
        }
;System.out.println(4);
        return new ReviewCommentDto(reviewComment);
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
    public ReviewCommentDto modify(Long reviewId, Long commentId,ReviewCommentDto reviewCommentDto) {


        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                        ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                ));
        reviewComment.setComment(reviewCommentDto.getComment());


        reviewCommentRepository.save(reviewComment);

        return new ReviewCommentDto(reviewComment);
    }

    /**
     * 댓글 삭제
     * @param commentId
     * @return RevieCommendDto - id,commend, userId
     *
     * @author -- 이광석
     * @since -- 25.01.17
     */
    public ReviewCommentDto delete(Long commentId) {
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                        ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                ));

        ReviewCommentDto reviewCommentDto =new ReviewCommentDto(reviewComment);
        reviewCommentRepository.delete(reviewComment);

        return reviewCommentDto;
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
    public Boolean recommend(Long commentId,Long memberId) {
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

        Set<Member> members  = reviewComment.getRecommend();
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
     *단일 코멘트 찾기
     * @param commentId
     * @return ReviewCommentDto - id, comment, userId
     *
     * @author 이광석
     * @since 25.02.03
     */
    public ReviewCommentDto findById(Long commentId) {
        ReviewComment reviewComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
        ));

        return new ReviewCommentDto(reviewComment);
    }


    /**
     * 대댓글 출력
     * @param commentId
     * @return List<ReviewCommentDto>
     *
     * @since 25.02.05
     * @author 이광석
     */
    public List<ReviewCommentDto> findReplies(Long commentId) {
        ReviewComment parent = reviewCommentRepository.findById(commentId)
                .orElseThrow(()->new ReviewException(
                        ReviewErrorCode.COMMENT_NOT_FOUND.getStatus(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getErrorCode(),
                        ReviewErrorCode.COMMENT_NOT_FOUND.getMessage()
                ));
        List<ReviewCommentDto> sons = reviewCommentRepository.findByParent(parent);
        return sons;
    }
}
