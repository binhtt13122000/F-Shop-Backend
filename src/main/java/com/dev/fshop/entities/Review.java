package com.dev.fshop.entities;


import com.dev.fshop.generator.entities.ProductIdPrefixedSequenceReviewIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_review")
    @GenericGenerator(
            name = "sequence_review",
            strategy = "com.dev.fshop.generator.entities.ProductIdPrefixedSequenceReviewIdGenerator",
            parameters = {
                    @Parameter(name = ProductIdPrefixedSequenceReviewIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = ProductIdPrefixedSequenceReviewIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = ProductIdPrefixedSequenceReviewIdGenerator.VALUE_PREFIX_PARAMETER, value = "REVIEW_"),
                    @Parameter(name = ProductIdPrefixedSequenceReviewIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
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
        return proId == null ? product.getProductId() : proId;
    }
}
