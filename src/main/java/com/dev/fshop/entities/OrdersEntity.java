package com.dev.fshop.entities;


import com.dev.fshop.embedded.OrderDetail;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Orders")
public class OrdersEntity {

    @Id
    @Column(name = "orderId", nullable = false, unique = false)
    private String orderId;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "promo")
    private float promo;
    @Column(name = "createAt")
    private Date createAt;
    @Column(name = "orderTotal")
    private float orderTotal;
    @Column(name = "status")
    private boolean status;


    @OneToMany(mappedBy = "ordersEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "ordersEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ReviewEntity> reviewEntities;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customerEntity;
}
