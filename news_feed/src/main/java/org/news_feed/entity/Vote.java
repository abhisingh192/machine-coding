package org.news_feed.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vote {

    private Long id;
    private VoteType voteType;

    private Long postId;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
