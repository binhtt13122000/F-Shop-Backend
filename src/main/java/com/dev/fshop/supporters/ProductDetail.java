package com.dev.fshop.supporters;

import com.dev.fshop.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
    private String proItemId;
    @Column(name = "proSize", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 20)
    @Schema(example = "XL")
    private String proSize;
    @Column(name = "proQuantity", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "2")
    private int proQuantity;
    @Column(name = "status", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "1")
    private int status;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String proId;

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }
}
