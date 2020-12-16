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
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItemId")
    private int orderItemId;
    @Column(name = "proId")
    private int proId;
    @Column(name = "orderId")
    private  int orderId;
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
}
