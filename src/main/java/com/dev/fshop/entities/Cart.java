package com.dev.fshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
