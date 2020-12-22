package com.dev.fshop.supporters;


import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
    @Id
    @Column(name = "orderItemId", nullable = false, unique = true)
    @JsonIgnore
    private String orderItemId;

    //quantity
    @Column(name = "orderItemQuan", nullable = false)
    @NotNull
    @NotBlank
    @Min(1)
    @Schema(example = "3")
    private int orderItemQuan;
    //price
    @Column(name = "orderItemPrice", nullable = false)
    @NotNull
    @NotBlank
    @Min(1)
    @Schema(example = "180000")
    private float orderItemPrice;

    //orderSize
    @Column(name = "orderSize", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "XL")
    private String orderSize;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String proId;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String orderId;

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }

    public String getOrderId() {
        return orderId == null ? orders.getOrderId() : orderId;
    }
}
