package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private int reviewId;
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "proId")
    private int proId;
    @Column(name = "star")
    private int star;
    @Column(name = "content")
    private String content;
}
