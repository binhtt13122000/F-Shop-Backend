package com.dev.fshop.supporters;

import com.dev.fshop.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
    @Column(name = "dícountId", nullable = false, unique = true, updatable = false)
    @JsonIgnore
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
    private String proId;

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }
}
