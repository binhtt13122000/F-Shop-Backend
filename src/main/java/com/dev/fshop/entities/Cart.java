package com.dev.fshop.entities;

import com.dev.fshop.generator.entities.UserIdPrefixedSequenceCartIdGenerator;
import com.dev.fshop.utils.Regex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
            strategy = "com.dev.fshop.generator.entities.UserIdPrefixedSequenceCartIdGenerator",
            parameters = {
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.VALUE_PREFIX_PARAMETER, value = "CART_"),
                    @Parameter(name = UserIdPrefixedSequenceCartIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String cartId;
    @Column(name = "cartDescription", nullable = false, unique = true)
    @NotNull(message = "Cart description is not null")
    @Size(max = 50, message = "Max Size of Cart description is 100 characters!")
    @Schema(example = "Giỏ hàng quần xinh")
    private String cartDescription;

    @Column(name = "cartTotal", nullable = false)
    @NotNull
    @Schema(example = "1200000")
    private float cartTotal;

    @Column(name = "createTime", nullable = false)
    @NotNull
    private Date createTime;

    @Column(name = "status", nullable = false)
    @NotNull
    private int status;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    @Transient
    @Size(max = 40)
    private String userId;

    public String getUserId() {
        return userId == null ? account.getUserId() : userId;
    }
}
