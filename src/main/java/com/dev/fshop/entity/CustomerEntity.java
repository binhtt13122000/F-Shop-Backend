package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private String userId;

    @Column(name = "roleId")
    private int roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

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
}
