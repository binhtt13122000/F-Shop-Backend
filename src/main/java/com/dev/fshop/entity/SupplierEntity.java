package com.dev.fshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierId")
    private int supplierId;
    @Column(name = "supplierName")
    private String supplierName;

    @OneToMany(mappedBy = "ProductEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductEntity> productEntities;
}
