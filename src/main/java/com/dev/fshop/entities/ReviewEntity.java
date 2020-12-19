package com.dev.fshop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Review")
@Schema(name = "Review")
public class ReviewEntity {
    @Id
    @JsonIgnore
    @Column(name = "reviewId", nullable = false, unique = true)
    private String reviewId;
    @Column(name = "star")
    @NotNull
    @NotBlank
    @Min(1)
    @Max(5)
    private int star;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private OrdersEntity ordersEntity;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private ProductEntity productEntity;

    @Transient
    private String orderId;

    @Transient String proId;

}
