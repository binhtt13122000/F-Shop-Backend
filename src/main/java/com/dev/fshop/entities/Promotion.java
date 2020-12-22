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
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Promotion")
@Schema(name = "Promotion")
public class Promotion implements Serializable {

    //promotion id
    @Id
    @JsonIgnore
    @Column(name = "promotionID", nullable = false, unique = true)
    private String promotionID;

    @Column(name = "promotionName", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Schema(example = "GIẢM GIÁ 10%")
    private String promotionName;


    @Column(name = "promo", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "20")
    private float promo;

    @Column(name = "status", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "1")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    @Transient
    private String userId;

    public String getUserId() {
        return account != null ? account.getUserId() : null;
    }
}
