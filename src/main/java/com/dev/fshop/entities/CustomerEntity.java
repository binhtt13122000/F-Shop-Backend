package com.dev.fshop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Customer")
@Schema(name = "User")
public class CustomerEntity {
    @Id
    @NotBlank
    @NotNull
    @Size(max = 40)
    @Column(name = "userId", nullable = false, unique = true)
    private String userId;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @Column(name = "birthDate")
    @NotBlank
    @NotNull
    private Date birthDate;

    @Column(name = "phoneNumber")
    @NotBlank
    @NotNull
    @Size(max = 15)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @Column(name = "gender")
    @NotBlank
    @NotNull
    private boolean gender;

    @Column(name = "country")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String country;

    @Column(name = "address")
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String address;

    @Column(name = "registeredAt")
    @NotNull
    @NotBlank
    private Date registeredAt;

    @Column(name = "lastLogin")
    @NotNull
    @NotBlank
    private  Date lastLogin;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    @NotNull
    @NotBlank
    private boolean status;

    @Transient
    @NotNull
    @NotBlank
    @Min(1)
    @Max(3)
    private int roleId;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JsonIgnore
    private Collection<CommentEntity> commentEntities;


    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<OrdersEntity> ordersEntities;


    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<PromotionEntity> promotionEntities;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private RoleEntity roleEntity;
}
