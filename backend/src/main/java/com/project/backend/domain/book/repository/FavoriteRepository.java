package com.project.backend.domain.book.repository;

import com.project.backend.domain.book.entity.Favorite;
import com.project.backend.domain.book.key.FavoriteId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * -- 찜 저장소 --
 *
 * @author -- 정재익 --
 * @since -- 1월 27일 --
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    List<Favorite> findById_MemberId(Long memberId);
    boolean existsById_MemberId(Long memberId);

    /**
     * -- 회원이 삭제될때 찜한 데이터도 삭제되는 메소드 --
     *
     * @param -- memberId --
     * @author -- 정재익 --
     * @since -- 2월 9일 --
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);
}