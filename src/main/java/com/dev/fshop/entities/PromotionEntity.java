package com.dev.fshop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Promotion")
@Schema(name = "Promotion")
public class PromotionEntity {

    @Id
    @JsonIgnore
    @Column(name = "promotionID", nullable = false, unique = true)
    private String promotionID;

    @Column(name = "promotionName")
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String promotionName;


    @Column(name = "promo")
    @NotNull
    @NotBlank
    @Min(10)
    @Max(80)
    private float promo;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private CustomerEntity customerEntity;

    @Transient
    private String userId;
}
