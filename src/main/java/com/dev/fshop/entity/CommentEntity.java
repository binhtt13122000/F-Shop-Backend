package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private int commentId;
    @Column(name = "userId")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "proId")
    private int proId;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "content")
    private String content;
}
