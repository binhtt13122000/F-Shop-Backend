package com.dev.fshop.embedded;



import com.dev.fshop.entity.OrdersEntity;
import com.dev.fshop.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrderDetail")
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
