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
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Review")
@Schema(name = "Review")
public class Review implements Serializable {
    //id
    @Id
    @JsonIgnore
    @Column(name = "reviewId", nullable = false, unique = true)
    private String reviewId;

    //star
    @Column(name = "star", nullable = false)
    @NotNull
    @NotBlank
    @Min(1)
    @Max(5)
    @Schema(example = "3")
    private int star;

    //content
    @Column(name = "content")
    @NotBlank
    @Schema(example = "đẹp")
    private String content;

    //createTime
    @Column(name = "createTime", nullable = false)
    @NotNull
    @NotBlank
    private Date createTime;

    //status
    @Column(name = "status", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "1")
    private int status;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String orderId;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String proId;

    public String getOrderId() {
        return orderId == null ? orders.getOrderId() : orderId;
    }

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }
}
