package com.project.backend.domain.review.review.repository;

import com.project.backend.domain.review.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 리뷰 레파지토리
 */
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Optional<Review> findByMemberId(long id);
}
