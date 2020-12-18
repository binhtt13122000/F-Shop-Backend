package com.dev.fshop.entity;


import com.dev.fshop.embedded.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class ProductEntity {

    @Id
    @Column(name = "proId", nullable = false, unique = false)
    private String proId;
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

    @ManyToOne
    @JoinColumn(name = "proTypeId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SupplierEntity supplierEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ReviewEntity> reviewEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<CommentEntity> commentEntities;
}
