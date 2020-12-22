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
@Table(name = "Supplier")
@Schema(name = "Supplier")
public class Supplier implements Serializable {
    //id
    @Id
    @Column(name = "supplierId", nullable = false, unique = true, updatable = false)
    @NotNull
    @NotBlank
    @Schema(example = "SUP_0001")
    @Size(max = 40)
    private String supplierId;
    //name
    @Column(name = "supplierName", nullable = false)
    @Schema(example = "GUCCI")
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String supplierName;

    //relation
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<Product> products;
}
