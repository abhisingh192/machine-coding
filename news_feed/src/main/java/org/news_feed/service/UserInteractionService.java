package org.news_feed.service;

import lombok.Data;
import org.news_feed.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserInteractionService {

    /*
    this class will implement functionalities to post, like, comment, and share news articles
     */

    private final UserAuthenticationService authService;
    private final Map<Long, FeedItem> feedItems = new HashMap<>();
    private static long postIdCounter = 1;
    private static long voteIdCounter = 1;


    public UserInteractionService(UserAuthenticationService authService) {
        this.authService = authService;
    }

    public void postArticle(String username, String articleContent) {
        // Implementation for posting an article

        if (authService.getLoggedInUsers().contains(username)) {
            Post post = Post.builder().id(postIdCounter++).content(articleContent).userName(username)
                    .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).
                    .build();

            feedItems.put(post.getId(), post);

            System.out.println("Article posted by " + username);
        } else {
            System.out.println("User must be logged in to post an article");
        }
    }

    public void postReply(String username, Long postId, String replyContent) {
        // Implementation for posting a reply to an article

        if (authService.getLoggedInUsers().contains(username)) {
            if (feedItems.containsKey(postId) && feedItems.get(postId) instanceof Post) {
                Reply reply = Reply.builder().id(postIdCounter++).content(replyContent).userName(username).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now())
                        .parentPostId(postId)
                        .build();
//                feedItems.put(reply.getId(), reply);

                System.out.println("Reply posted by " + username + " to post ID " + postId);
            } else {
                System.out.println("Post with ID " + postId + " not found");
            }
        } else {
            System.out.println("User must be logged in to post a reply");
        }
    }

    public void vote(String username, Long postId, boolean isUpvote) {
        // Implementation for voting on a post

        if (authService.getLoggedInUsers().contains(username)) {
            if (feedItems.containsKey(postId) && feedItems.get(postId) instanceof Post) {
                Post post = (Post) feedItems.get(postId);
                if (isUpvote) {
                    post.setUpvoteCount(post.getUpvoteCount() + 1);
                    System.out.println("Post ID " + postId + " upvoted by " + username);
                    addVote(post, postId, username, VoteType.UPVOTE);

                } else {
                    post.setDownvoteCount(post.getDownvoteCount() + 1);
                    addVote(post, postId, username, VoteType.UPVOTE);

                    System.out.println("Post ID " + postId + " downvoted by " + username);
                }
            } else {
                System.out.println("Post with ID " + postId + " not found");
            }
        } else {
            System.out.println("User must be logged in to vote on a post");
        }
    }

    public void follow(String username, String followedUsername) {
        // Implementation for following another user

        if (authService.getLoggedInUsers().contains(username)) {
            User follower = authService.getUsers().get(username);
            User followee = authService.getUsers().get(followedUsername);

            if (follower != null && followee != null) {
                follower.getFollows().add(followee);
                System.out.println(username + " is now following " + followedUsername);
            } else {
                System.out.println("One or both users not found");
            }
        } else {
            System.out.println("User must be logged in to follow another user");
        }
    }

    private void addVote(Post post, Long postId, String username, VoteType voteType) {
        Vote vote = new Vote();
        vote.setVoteType(voteType);
        vote.setPostId(postId);
        vote.setUserName(username);
        vote.setCreatedAt(LocalDateTime.now());
        vote.setUpdatedAt(LocalDateTime.now());
        vote.setId(voteIdCounter++);
        post.getVotes().add(vote);
    }
}
