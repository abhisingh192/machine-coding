package org.news_feed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends FeedItem {
    private Long parentPostId;

    @Builder
    public Reply(Long id, String userName, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long parentPostId) {
        super(id, userName, content, createdAt, updatedAt);
        this.parentPostId = parentPostId;
    }
}
