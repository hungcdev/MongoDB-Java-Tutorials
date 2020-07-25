package com.hungcdev.mongodb.data;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class Post {
    private String id;
    private String title;
    private String user;
    private String content;
    private List<String> tags;
    private boolean enable;

}
