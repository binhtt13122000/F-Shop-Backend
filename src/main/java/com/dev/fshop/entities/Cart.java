package com.dev.fshop.entities;

import com.dev.fshop.generator.entites.UserIdPrefixedSequenceCartIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Cart")
@Schema(name = "Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    //id
    @Id
    @Column(name = "cartId", nullable = false, updatable = false, unique = true)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_cart")
    @GenericGenerator(
            name = "sequence_cart",
            strategy = "com.dev.fshop.generator.enti.UserIdPrefixedSequenceCartIdGenerator",
            parameters = {
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.VALUE_PREFIX_PARAMETER, value = "CART_"),
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String cartId;

    @Column(name = "cartTotal", nullable = false)
    @NotNull
    @NotBlank
    @Min(1)
    @Schema(example = "1200000")
    private float cartTotal;

    @Column(name = "createTime", nullable = false)
    @NotNull
    @NotBlank
    private Date createTime;

    @Column(name = "status", nullable = false)
    @NotNull
    @NotBlank
    private int status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String userId;

    public String getUserId() {
        return userId == null ? account.getUserId() : userId;
    }
}
