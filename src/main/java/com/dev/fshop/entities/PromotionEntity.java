package com.dev.fshop.entities;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Promotion")
public class PromotionEntity {

    @Id
    @Column(name = "promotionID", nullable = false, unique = true)
    private String promotionID;
    @Column(name = "promotionName")
    private String promotionName;

    @Column(name = "promo")
    private float promo;
    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customerEntity;
}
