package com.dev.fshop.supporters;


import com.dev.fshop.entities.Orders;
import com.dev.fshop.entities.Product;
import com.dev.fshop.generator.supporters.UserIdPrefixedSequenceOrderDetailIdGenerator;
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
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderDetail")
public class OrderDetail implements Serializable {
    @Id
    @Column(name = "orderItemId", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_orderDetail")
    @GenericGenerator(
            name = "sequence_orderDetail",
            strategy = "com.dev.fshop.generator.supporters.UserIdPrefixedSequenceOrderDetailIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = UserIdPrefixedSequenceOrderDetailIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = UserIdPrefixedSequenceOrderDetailIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @org.hibernate.annotations.Parameter(name = UserIdPrefixedSequenceOrderDetailIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORDER_DETAIL_"),
                    @org.hibernate.annotations.Parameter(name = UserIdPrefixedSequenceOrderDetailIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    @Schema(example = "ORDER_DETAIL_0001")
    private String orderItemId;

    //quantity
    @Column(name = "orderItemQuan", nullable = false)
    @NotNull
    @Min(1)
    @Schema(example = "3")
    private int orderItemQuan;
    //price
    @Column(name = "orderItemPrice", nullable = false)
    @NotNull
    @Min(1)
    @Schema(example = "180000")
    private float orderItemPrice;

    //orderSize
    @Column(name = "orderSize", nullable = false)
    @NotNull
    @Schema(example = "XL")
    private String orderSize;

    @Column(name = "createAt")
    @NotNull
    private Date createAt;

    @Column(name = "status")
    @NotNull
    private int status;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Orders orders;

    @Transient
    @Size(max = 40)
    private String productId;

    @Transient
    @Size(max = 40)
    private String orderId;

    public String getProId() {
        return productId == null ? product.getProductId() : productId;
    }

    public String getOrderId() {
        return orderId == null ? orders.getOrderId() : orderId;
    }
}
