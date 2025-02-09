package com.project.backend.domain.book.entity;

import com.project.backend.domain.book.key.FavoriteId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * -- 찜 엔티티 --
 *
 * @author -- 정재익 --
 * @since -- 1월 27일 --
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {

    @EmbeddedId
    private FavoriteId id;
}
