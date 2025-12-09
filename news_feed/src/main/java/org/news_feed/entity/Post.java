package org.news_feed.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Post extends FeedItem {
    private List<Reply> replies;
    private List<Vote> votes;
    private long upvoteCount;
    private long downvoteCount;

    @Builder
    Post(Long id, String userName, String content, LocalDateTime createdAt, LocalDateTime updatedAt, List<Reply> replies, List<Vote> votes, long upvoteCount, long downvoteCount) {
        super(id, userName, content, createdAt, updatedAt);
        this.replies = null;
        this.votes = null;
        this.upvoteCount = 0;
        this.downvoteCount = 0;
    }

    // agar construct me null and zero wala nahi diya to set karne hi nahi aa raha tha
}
