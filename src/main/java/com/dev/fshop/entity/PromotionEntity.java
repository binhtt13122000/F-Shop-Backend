package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Promotion")
public class PromotionEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotionID")
    private int promotionID;
    @Column(name = "promotionName")
    private String promotionName;
    @Column(name = "userId")
    private String userId;
    @Column(name = "promo")
    private float promo;
    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "userId") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customerEntity;
}
