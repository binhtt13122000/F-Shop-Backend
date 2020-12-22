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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Category")
@Schema(name = "Category")
public class Category implements Serializable {
    //id
    @Id
    @Column(name = "proTypeId", nullable = false, unique = true)
    @Schema(example = "TYPE_0001")
    @JsonIgnore
    private String proTypeId;

    //name
    @Column(name = "proTypeName", nullable = false)
    @Schema(example = "T-Shirt")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String proTypeName;


    //relation
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<Product> products;

}
