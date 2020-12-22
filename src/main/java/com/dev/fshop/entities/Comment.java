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
import javax.persistence.Transient;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Comment")
@Schema(name = "Comment")
public class Comment implements Serializable {
    @Id
    @JsonIgnore
    @Column(name = "commentId", nullable = false, unique = true)
    private String commentId;

    //name
    @Column(name = "name", nullable = false)
    @Size(max = 50)
    @NotNull
    @NotBlank
    @Schema(example = "Trương Thanh Bình")
    private String name;

    //phone
    @Column(name = "phoneNumber")
    @Size(max = 15)
    @NotBlank
    @Schema(example = "0335579880")
    private String phoneNumber;

    //content
    @Column(name = "content", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 100)
    @Schema(name = "ngu vậy man!")
    private String content;

    //customer entity
    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    //product entity
    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    //replace productEntity
    @Transient
    @NotBlank
    @Size(max = 40)
    private String proId;

    //replace userEntity
    @NotBlank
    @Transient
    @Size(max = 40)
    private String userId;

    public String getUserId() {
        return userId == null ? (account != null) ? account.getUserId() : null : userId;
    }

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }
}
