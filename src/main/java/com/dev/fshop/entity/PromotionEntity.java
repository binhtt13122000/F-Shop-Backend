package com.dev.fshop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

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
    @JoinColumn(name = "userId") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customerEntity;
}
