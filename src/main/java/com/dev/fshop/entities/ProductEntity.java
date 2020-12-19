package com.dev.fshop.entities;


import com.dev.fshop.embedded.OrderDetail;
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
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
@Schema(name = "Product")
public class ProductEntity {

    @Id
    @Column(name = "proId", nullable = false, unique = true)
    @JsonIgnore
    private String proId;
    @Column(name = "proName")
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String proName;
    @Column(name = "proPrice")
    @Min(1)
    @NotNull
    @NotBlank
    private float proPrice;
    @Column(name = "proQuantity")
    @NotNull
    @NotBlank
    @Min(1)
    private int proQuantity;
    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(name = "proDescription")
    private String proDescription;
    @Column(name = "createAt")
    @NotBlank
    @NotNull
    private Date createAt;
    @Column(name = "updateAt")
    private Date updateAt;
    @Column(name = "discount")
    @NotNull
    @NotBlank
    private float discount;

    @Transient
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String categoryId;

    @Transient
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String supplierId;

    //relation
    @ManyToOne
    @JoinColumn(name = "proTypeId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private SupplierEntity supplierEntity;


    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JsonIgnore
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<ReviewEntity> reviewEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JsonIgnore
    private Collection<CommentEntity> commentEntities;


}
