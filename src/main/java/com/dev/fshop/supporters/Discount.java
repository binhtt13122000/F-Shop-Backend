package com.dev.fshop.supporters;

import com.dev.fshop.entities.Product;
import com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceDiscountIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "Discount")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount implements Serializable {
    @Id
    @Column(name = "discountId", nullable = false, unique = true, updatable = false)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_discount")
    @GenericGenerator(
            name = "sequence_discount",
            strategy = "com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceDiscountIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceDiscountIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceDiscountIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceDiscountIdGenerator.VALUE_PREFIX_PARAMETER, value = "DISCOUNT_"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceDiscountIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String discountId;
    @Column(name = "discount", nullable = false)
    @Max(10)
    @Min(5)
    @NotBlank
    @NotNull
    @Schema(example = "10")
    private float discount;
    @Column(name = "startTime", nullable = false)
    @NotBlank
    @NotNull
    private Date startTime;
    @Column(name = "endTime", nullable = false)
    @NotBlank
    @NotNull
    private Date endTime;
    @Column(name = "status", nullable = false)
    @NotBlank
    @NotNull
    @Schema(example = "true")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String productId;

    public String getProId() {
        return productId == null ? product.getProductId() : productId;
    }
}
