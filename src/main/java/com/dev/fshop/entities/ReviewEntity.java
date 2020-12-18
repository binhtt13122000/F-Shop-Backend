package com.dev.fshop.entities;


import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Review")
public class ReviewEntity {
    @Id
    @Column(name = "reviewId", nullable = false, unique = true)
    private String reviewId;
    @Column(name = "star")
    private int star;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrdersEntity ordersEntity;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductEntity productEntity;
}
