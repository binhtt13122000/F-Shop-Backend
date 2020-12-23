package com.dev.fshop.entities;


import com.dev.fshop.generator.enti.UserIdPrefixedSequenceOrderIdGenerator;
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
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Orders")
@Schema(name = "Order")
public class Orders implements Serializable {
    //id
    @Id
    @JsonIgnore
    @Column(name = "orderId", nullable = false, unique = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_order")
    @GenericGenerator(
            name = "sequence_order",
            strategy = "com.dev.fshop.generator.enti.UserIdPrefixedSequenceOrderIdGenerator",
            parameters = {
                    @Parameter(name = UserIdPrefixedSequenceOrderIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = UserIdPrefixedSequenceOrderIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = UserIdPrefixedSequenceOrderIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORDER_"),
                    @Parameter(name = UserIdPrefixedSequenceOrderIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String orderId;
    //fullname
    @Column(name = "fullname", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Schema(example = "Trương Thanh Bình")
    private String fullname;
    //phone number
    @NotNull
    @NotBlank
    @Size(max = 15)
    @Column(name = "phoneNumber", nullable = false)
    @Schema(example = "0335579880")
    private String phoneNumber;
    //address
    @Column(name = "address", nullable = false)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String address;
    //create time
    @Column(name = "createAt", nullable = false)
    @NotNull
    @NotBlank
    private Date createAt;
    //orderTotal
    @NotNull
    @NotBlank
    @Min(1)
    @Column(name = "orderTotal", nullable = false)
    @Schema(example = "1200000")
    private float orderTotal;
    //orderTotal
    @NotNull
    @NotBlank
    @Min(1)
    @Column(name = "realTotal", nullable = false)
    @Schema(example = "1150000")
    private float realTotal;
    //status
    @NotNull
    @NotBlank
    @Column(name = "status", nullable = false)
    private int status;
    //isOnline
    @NotNull
    @NotBlank
    @Column(name = "isOnline", nullable = false)
    private boolean isOnline;

    //relation
    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account seller;

    @ManyToOne
    @JoinColumn(name = "promotionId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Promotion promotion;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String userId;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String sellerId;

    @Transient
    @NotBlank
    @Size(max = 40)
    private String promotionId;

    public String getUserId() {
        return userId == null ? account.getUserId() : userId;
    }

    public String getSellerId() {
        return sellerId == null ? seller.getUserId() : sellerId;
    }

    public String getPromotionId() {
        return promotionId == null ? (promotion != null) ? promotion.getPromotionID() : null : promotionId;
    }
}
