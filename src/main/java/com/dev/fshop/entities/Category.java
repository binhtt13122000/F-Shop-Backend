package com.dev.fshop.entities;


import com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator;
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
@Table(name = "Category")
@Schema(name = "Category")
public class Category implements Serializable {
    //id
    @Id
    @Column(name = "proTypeId", nullable = false, unique = true)
    @Schema(example = "TYPE_0001")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_category")
    @GenericGenerator(
            name = "sequence_category",
            strategy = "com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TYPE_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String proTypeId;

    //name
    @Column(name = "proTypeName", nullable = false)
    @Schema(example = "T-Shirt")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String proTypeName;


    //relation
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore
//    private Collection<Product> products;

}
