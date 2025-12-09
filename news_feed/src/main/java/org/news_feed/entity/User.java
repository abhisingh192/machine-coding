package org.news_feed.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @NonNull
    private  String userName;
    @NonNull
    private  String password;
    @NonNull
    private  String name;
    private List<Post> posts;
    private Feed feed;
    private List<User> follows;


}
