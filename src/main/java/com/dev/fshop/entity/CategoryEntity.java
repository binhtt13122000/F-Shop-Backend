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
@Table(name = "Category")
public class CategoryEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proTypeId")
    private int proTypeId;
    @Column(name = "proTypeName")
    private String proTypeName;

    @OneToMany(mappedBy = "PromotionEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<PromotionEntity> promotionEntities;

}
