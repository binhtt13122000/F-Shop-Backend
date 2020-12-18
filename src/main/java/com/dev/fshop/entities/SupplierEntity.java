package com.dev.fshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Supplier")
public class SupplierEntity {
    @Id
    @Column(name = "supplierId", nullable = false, unique = false)
    private String supplierId;
    @Column(name = "supplierName")
    private String supplierName;

    @OneToMany(mappedBy = "supplierEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductEntity> productEntities;
}
