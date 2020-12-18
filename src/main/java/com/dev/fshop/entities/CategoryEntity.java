package com.dev.fshop.entities;


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
import javax.persistence.CascadeType;
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
