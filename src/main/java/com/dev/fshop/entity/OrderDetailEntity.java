package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "userId")
    private String userId;
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

    @OneToMany(mappedBy = "OrderItemEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderItemEntity> orderItemEntities;

    @OneToMany(mappedBy = "ReviewEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ReviewEntity> reviewEntities;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customerEntity;
}
