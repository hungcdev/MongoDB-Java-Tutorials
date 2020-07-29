package com.hungcdev.mongodb.data;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    private String id;
    private String title;
    private String user;
    private String content;
    private List<String> tags;
    private List<Integer> numbers;
    private List<Comment> comments;
    private PostQuality postQuality;
    private int view;
    private boolean enable;
}
