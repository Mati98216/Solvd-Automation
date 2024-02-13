package com.solvd.laba.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private Integer id;
    private Post post;
    private String name;
    private String email;
    private String body;
}
