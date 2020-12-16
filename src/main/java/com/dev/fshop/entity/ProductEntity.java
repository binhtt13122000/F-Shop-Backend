package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proId")
    private int proId;
    @Column(name = "supplierId")
    private int supplierId;
    @Column(name = "proTypeId")
    private int proTypeId;
    @Column(name = "proName")
    private String proName;
    @Column(name = "proPrice")
    private float proPrice;
    @Column(name = "proQuantity")
    private int proQuantity;
    @Column(name = "proDescription")
    private String proDescription;
    @Column(name = "createAt")
    private Date createAt;
    @Column(name = "updateAt")
    private Date updateAt;
    @Column(name = "discount")
    private float discount;
}
