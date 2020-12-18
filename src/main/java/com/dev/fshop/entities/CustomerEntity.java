package com.dev.fshop.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    @Column(name = "userId", nullable = false, unique = true)
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = false)
    private String email;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "registeredAt")
    private Date registeredAt;

    @Column(name = "lastLogin")
    private  Date lastLogin;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private boolean status;


    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<CommentEntity> commentEntities;


    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrdersEntity> ordersEntities;


    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<PromotionEntity> promotionEntities;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private RoleEntity roleEntity;
}