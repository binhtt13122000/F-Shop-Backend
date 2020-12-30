package com.dev.fshop.entities;


import com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator;
import com.dev.fshop.supporters.Discount;
import com.dev.fshop.supporters.ProductDetail;
import com.dev.fshop.supporters.ProductImage;
import com.dev.fshop.validation.existIn.ExistIn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Id
    @Column(name = "proId", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_product")
    @GenericGenerator(
            name = "sequence_product",
            strategy = "com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String productId;

    @Column(name = "proName", nullable = false)
    @NotNull(message = "Product name is not null!")
    @Size(max = 50, min = 10, message = "Product name must be at most 50 characters and at least 10 characters!")
    @Schema(name = "ÁO THUN NAM T249")
    private String productName;

    @Column(name = "proPrice", nullable = false)
    @Min(value = 1000, message = "The lowest price of the product is 1000 VNĐ")
    @NotNull(message = "Product price is not null")
    @Schema(name = "100000")
    private float productPrice;

    @Column(name = "realPrice", nullable = false)
    @Min(value = 1000, message = "The lowest real price of the product is 1000 VNĐ")
    @NotNull(message = "Real price is not null!")
    @Schema(name = "120000")
    private float realPrice;

    @Column(name = "proDescription", nullable = false)
    @NotNull(message = "description is not null!")
    @Size(max = 100, min = 10,message = "Product description must be at most 100 characters and at least 10 characters!!")
    @Schema(example = "Áo rất đẹp!")
    private String productDescription;

    @Column(name = "createAt", nullable = false)
    private Date createAt;

    @Column(name = "updateAt")
    private Date updateAt;


    @Column(name = "status", nullable = false)
    private int status;

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
    @Size(max = 40, message = "categoryId must be at most 40 characters!")
    @NotNull(message = "Category is not null!")
    @ExistIn(message = "Category is not exist!", fieldName = "categoryId", className = "Product")
    @Schema(example = "SUP_0001")
    private String categoryId;

    @Transient
    @Size(max = 40, message = "categoryId must be at most 40 characters!")
    @NotNull(message = "SupplierId is not null!")
    @ExistIn(message = "Supplier is not exist!", fieldName = "supplierId", className = "Product")
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
