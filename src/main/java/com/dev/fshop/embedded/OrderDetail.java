package com.dev.fshop.embedded;


import com.dev.fshop.entities.OrdersEntity;
import com.dev.fshop.entities.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderItem")
public class OrderDetail {
    @Id
    @Column(name = "orderItemId", nullable = false, unique = true)
    private String orderItemId;
    @Column(name = "orderItemQuan")
    private int orderItemQuan;
    @Column(name = "orderItemPrice")
    private float orderItemPrice;
    @Column(name = "discount")
    private float discount;
    @Column(name = "createAt")
    private Date createAt;
    @Column(name = "contend")
    private String contend;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrdersEntity ordersEntity;
}
