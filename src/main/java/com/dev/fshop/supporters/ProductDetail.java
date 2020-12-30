package com.dev.fshop.supporters;

import com.dev.fshop.entities.Product;
import com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceProductDetailIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Table
@Entity(name = "ProductDetail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetail implements Serializable {
    @Id
    @Column(name = "proItemId", nullable = false)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_proDetail")
    @GenericGenerator(
            name = "sequence_proDetail",
            strategy = "com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceProductDetailIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceProductDetailIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceProductDetailIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceProductDetailIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO_DETAIL_"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceProductDetailIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String proItemId;
    @Column(name = "proSize", nullable = false)
    @NotNull
    @Size(max = 20)
    @Schema(example = "XL")
    private String proSize;
    @Column(name = "proQuantity", nullable = false)
    @NotNull
    @Min(1)
    @Schema(example = "2")
    private int proQuantity;
    @Column(name = "status", nullable = false)
    @NotNull
    @Schema(example = "1")
    private int status;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    @Transient
    @Size(max = 40)
    private String proId;

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }
}
