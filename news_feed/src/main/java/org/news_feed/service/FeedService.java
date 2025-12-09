package org.news_feed.service;

import org.news_feed.entity.FeedItem;
import org.news_feed.entity.Post;
import org.news_feed.entity.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedService {

    /*
    this class will implement functionalities to fetch, filter, and manage news feeds for users
     */
    private final UserAuthenticationService authService;
    private final UserInteractionService interactionService;

    public FeedService(UserAuthenticationService authService, UserInteractionService interactionService) {
        this.authService = authService;
        this.interactionService = interactionService;
    }


    public void showFeed(String username) {
        // Implementation for showing the news feed to a user
        System.out.println("Displaying news feed for user: " + username);
        if(authService.getLoggedInUsers().contains(username)) {
            // Fetch and display feed items
//            1. Followed users : posts by followed users appear first.
//            2. Score (= upvotes - downvotes): the higher the better.
//            3. Number of comments: higher the better.
//            4. Timestamp: more recent the better.
            List<String> followedUsernames = authService.getUsers().get(username).getFollows().stream()
                    .map(User::getUserName)
                    .collect(Collectors.toList());

            List<Post> followedUserFeedItems = interactionService.getFeedItems()
                    .values()
                    .stream()
                    .filter(item -> item instanceof Post && followedUsernames.contains(item.getUserName()))
                    .map(item -> (Post) item)   // ⬅️ now Stream<Post>
                    .sorted(
                            Comparator
                                    // 1. Score: higher is better
                                    .comparingLong((Post p) -> p.getUpvoteCount() - p.getDownvoteCount())
                                    // 2. Number of comments: higher is better
                                    .thenComparingInt(p -> p.getReplies().size())
                                    // 3. Timestamp: more recent is better
                                    .thenComparing(Post::getUpdatedAt)
                                    // make all three DESC
                                    .reversed()
                    )
                    .collect(Collectors.toList());


            List<Post> unfollowedUserFeedItems = interactionService.getFeedItems()
                    .values()
                    .stream()
                    .filter(item -> item instanceof Post && !followedUsernames.contains(item.getUserName()))
                    .map(item -> (Post) item)   // ⬅️ now Stream<Post>
                    .sorted(
                            Comparator
                                    // 1. Score: higher is better
                                    .comparingLong((Post p) -> p.getUpvoteCount() - p.getDownvoteCount())
                                    // 2. Number of comments: higher is better
                                    .thenComparingInt(p -> p.getReplies().size())
                                    // 3. Timestamp: more recent is better
                                    .thenComparing(Post::getUpdatedAt)
                                    // make all three DESC
                                    .reversed()
                    )
                    .collect(Collectors.toList());

            List<Post> userFeed = Stream.concat(followedUserFeedItems.stream(), unfollowedUserFeedItems.stream())
                    .collect(Collectors.toList());


            System.out.println("Feed items would be displayed here.");
        } else {
            System.out.println("User must be logged in to view the feed");
        }
    }
}
