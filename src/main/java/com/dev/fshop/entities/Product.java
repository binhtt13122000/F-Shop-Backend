package com.dev.fshop.entities;


import com.dev.fshop.generator.enti.StringPrefixedSequenceIdGenerator;
import com.dev.fshop.supporters.Discount;
import com.dev.fshop.supporters.ProductDetail;
import com.dev.fshop.supporters.ProductImage;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
@Schema(name = "Product")
public class Product implements Serializable {
    //proId
    @Id
    @Column(name = "proId", nullable = false, unique = true)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_product")
    @GenericGenerator(
            name = "sequence_product",
            strategy = "com.dev.fshop.generator.enti.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String proId;

    //proName
    @Column(name = "proName", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Schema(name = "ÁO THUN NAM T249")
    private String proName;

    //proPrice
    @Column(name = "proPrice", nullable = false)
    @Min(1)
    @NotNull
    @NotBlank
    @Schema(name = "100000")
    private float proPrice;

    //realPrice
    @Column(name = "realPrice", nullable = false)
    @Min(1)
    @NotNull
    @NotBlank
    @Schema(name = "120000")
    private float realPrice;

    //description
    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(name = "proDescription", nullable = false)
    @Schema(example = "Áo rất đẹp!")
    private String proDescription;

    //create
    @Column(name = "createAt", nullable = false)
    @NotBlank
    @NotNull
    private Date createAt;

    //updateAt
    @Column(name = "updateAt")
    @NotBlank
    private Date updateAt;


    @Column(name = "status", nullable = false)
    private int status;

    //relation
    @ManyToOne
    @JoinColumn(name = "proTypeId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Supplier supplier;

    @Transient
    @NotBlank
    @Size(max = 40)
    @Schema(example = "SUP_0001")
    private String categoryId;

    @Transient
    @NotBlank
    @Size(max = 40)
    @Schema(example = "TYPE_0001")
    private String supplierId;

    public String getCategoryId() {
        return categoryId == null ? category.getProTypeId() : categoryId;
    }

    public String getSupplierId() {
        return supplierId == null ? supplier.getSupplierId() : supplierId;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Discount> discounts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages;


}
