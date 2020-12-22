package com.dev.fshop.supporters;

import com.dev.fshop.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private String imageId;
    @Column(name = "createTime", nullable = false)
    @NotNull
    @NotBlank
    private Date createTime;
    @Column(name = "imgDescription")
    @NotBlank
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
