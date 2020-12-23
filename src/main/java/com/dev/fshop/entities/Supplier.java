package com.dev.fshop.entities;

import com.dev.fshop.generator.entites.StringPrefixedSequenceIdGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_supplier")
    @GenericGenerator(
            name = "sequence_supplier",
            strategy = "com.dev.fshop.generator.enti.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SUP_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
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
