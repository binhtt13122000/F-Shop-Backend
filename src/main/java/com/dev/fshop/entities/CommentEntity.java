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


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Comment")
@Schema(name = "Comment")
public class CommentEntity {
    @Id
    @JsonIgnore
    @Column(name = "commentId", nullable = false, unique = true)
    private String commentId;

    //name
    @Column(name = "name")
    @Size(max = 50)
    private String name;

    //phone
    @Column(name = "phoneNumber")
    @Size(max = 15)
    private String phoneNumber;

    //content
    @Column(name = "content")
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String content;

    //customer entity
    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private CustomerEntity customerEntity;

    //product entity
    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private ProductEntity productEntity;

    //replace productEntity
    @Transient
    @NotNull
    @NotBlank
    private String proId;
    //replace userEntity
    @NotNull
    @NotBlank
    @Transient
    private String userId;

    //getter
    public String getProId() {
        return (productEntity != null) ? productEntity.getProId() : null;
    }
    public String getUserId() {
        return (customerEntity != null) ? customerEntity.getUserId() : null;
    }
}
