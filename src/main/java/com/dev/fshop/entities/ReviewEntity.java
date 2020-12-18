package com.dev.fshop.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


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
