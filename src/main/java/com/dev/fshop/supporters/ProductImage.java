package com.dev.fshop.supporters;

import com.dev.fshop.entities.Product;
import com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceProductImageIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ProductImage")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage implements Serializable {
    @Id
    @Column(name = "imageId", nullable = false)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_proImage")
    @GenericGenerator(
            name = "sequence_proImage",
            strategy = "com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceProductImageIdGenerator",
            parameters = {
                    @Parameter(name = ProductIdPrefixedSequenceProductImageIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = ProductIdPrefixedSequenceProductImageIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = ProductIdPrefixedSequenceProductImageIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO_IMAGE_"),
                    @Parameter(name = ProductIdPrefixedSequenceProductImageIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    @Schema(example = "PRO_IMAGE_0001")
    private String imageId;

    @Column(name = "createTime", nullable = false)
    @NotNull
    @NotBlank
    private Date createTime;

    @Column(name = "imgDescription")
    @NotBlank
    @Schema(example = "Content of image is sport product.")
    private String imgDescription;

    @Column(name = "imgUrl", nullable = false)
    @NotBlank
    @NotNull
    private String imgUrl;

    @Column(name = "status", nullable = false)
    @NotBlank
    @NotNull
    private boolean status;

    @ManyToOne
    @JsonIgnore
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
