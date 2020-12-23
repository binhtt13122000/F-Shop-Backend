package com.dev.fshop.entities;


import com.dev.fshop.generator.entities.UserIdPrefixedSequencePromotionIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_prmotion")
    @GenericGenerator(
            name = "sequence_promotion",
            strategy = "com.dev.fshop.generator.entities.UserIdPrefixedSequencePromotionIdGenerator",
            parameters = {
                    @Parameter(name = UserIdPrefixedSequencePromotionIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = UserIdPrefixedSequencePromotionIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = UserIdPrefixedSequencePromotionIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROMO_"),
                    @Parameter(name = UserIdPrefixedSequencePromotionIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
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
