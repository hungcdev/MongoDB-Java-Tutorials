package com.hungcdev.mongodb.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private String user;
    private String message;
    private int likeNumber;
}
