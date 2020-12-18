package com.dev.fshop.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @Column(name = "proTypeId", nullable = false, unique = true)
    private String proTypeId;
    @Column(name = "proTypeName")
    private String proTypeName;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductEntity> productEntities;

}
