package com.dev.fshop.entities;

import com.dev.fshop.generator.enti.StringPrefixedSequenceIdGenerator;
import com.dev.fshop.generator.enti.UserIdPrefixedSequenceCommentIdGenerator;
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
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Comment")
@Schema(name = "Comment")
public class Comment implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_comment")
    @GenericGenerator(
            name = "sequence_comment",
            strategy = "com.dev.fshop.generator.enti.UserIdPrefixedSequenceCommentIdGenerator",
            parameters = {
                    @Parameter(name = UserIdPrefixedSequenceCommentIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = UserIdPrefixedSequenceCommentIdGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "_"),
                    @Parameter(name = UserIdPrefixedSequenceCommentIdGenerator.VALUE_PREFIX_PARAMETER, value = "CMT_"),
                    @Parameter(name = UserIdPrefixedSequenceCommentIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    @Column(name = "commentId", nullable = false, unique = true)
    private String commentId;

    //name
    @Column(name = "name", nullable = false)
    @Size(max = 50)
    @NotNull
    @NotBlank
    @Schema(example = "Trương Thanh Bình")
    private String name;

    //phone
    @Column(name = "phoneNumber")
    @Size(max = 15)
    @NotBlank
    @Schema(example = "0335579880")
    private String phoneNumber;

    //content
    @Column(name = "content", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 100)
    @Schema(name = "ngu vậy man!")
    private String content;

    //create time
    @Column(name = "createTime", nullable = false)
    @NotBlank
    @NotNull
    private Date createTime;

    @Column(name = "status", nullable = false)
    @NotNull
    @NotBlank
    private int status;

    //customer entity
    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Account account;

    //product entity
    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Product product;

    //replace productEntity
    @Transient
    @NotBlank
    @Size(max = 40)
    private String proId;

    //replace userEntity
    @NotBlank
    @Transient
    @Size(max = 40)
    private String userId;

    public String getUserId() {
        return userId == null ? (account != null) ? account.getUserId() : null : userId;
    }

    public String getProId() {
        return proId == null ? product.getProId() : proId;
    }
}
