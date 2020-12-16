package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
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

    @OneToMany(mappedBy = "ReviewEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ReviewEntity> reviewEntities;

    @OneToMany(mappedBy = "CommentEntity", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<CommentEntity> commentEntities;
}
