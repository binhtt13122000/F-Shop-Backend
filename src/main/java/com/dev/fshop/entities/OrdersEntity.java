package com.dev.fshop.entities;


import com.dev.fshop.embedded.OrderDetail;
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
import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Orders")
@Schema(name = "Order")
public class OrdersEntity {

    @Id
    @JsonIgnore
    @Column(name = "orderId", nullable = false, unique = false)
    private String orderId;
    @Column(name = "name")
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 15)
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "address")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String address;
    @Column(name = "promo")
    @Max(80)
    @Min(10)
    private float promo;
    @Column(name = "createAt")
    @NotNull
    @NotBlank
    private Date createAt;
    @NotNull
    @NotBlank
    @Column(name = "orderTotal")
    private float orderTotal;
    @NotNull
    @NotBlank
    @Column(name = "status")
    private boolean status;

    @Transient
    private String userId;
    @OneToMany(mappedBy = "ordersEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "ordersEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<ReviewEntity> reviewEntities;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private CustomerEntity customerEntity;
}
