package com.dev.fshop.supporters;

import com.dev.fshop.entities.Cart;
import com.dev.fshop.entities.Product;
import com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceCartDetailGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "CartDetail")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDetail implements Serializable {
    @Id
    @Column(name = "cartItemId", nullable = false)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_cartDetail")
    @GenericGenerator(
            name = "sequence_cartDetail",
            strategy = "com.dev.fshop.generator.supporters.ProductIdPrefixedSequenceCartDetailGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceCartDetailGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceCartDetailGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceCartDetailGenerator.VALUE_PREFIX_PARAMETER, value = "CART_DETAILS_"),
                    @org.hibernate.annotations.Parameter(name = ProductIdPrefixedSequenceCartDetailGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String cartItemId;

    @Column(name = "cartSize", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 20)
    @Schema(example = "XL")
    private String cartSize;

    @Column(name = "cartQuantity", nullable = false)
    @NotNull
    @NotBlank
    @Min(1)
    @Schema(example = "XL")
    private int cartQuantity;

    @Column(name = "cartItemPrice", nullable = false)
    @NotNull
    @NotBlank
    @Min(1)
    @Schema(example = "80000")
    private float cartItemPrice;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cart cart;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String proId;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String cartId;

    public String getProId() {
        return proId == null ? product.getProductId() : proId;
    }

    public String getCartId() {
        return cartId == null ? cart.getCartId() : cartId;
    }
}
