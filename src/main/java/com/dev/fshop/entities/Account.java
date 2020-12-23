package com.dev.fshop.entities;


import com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator;
import com.dev.fshop.utils.Regex;
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
@Table(name = "Users")
@Schema(name = "User")
public class Account implements Serializable {
    @Id
    @Column(name = "userId", nullable = false, unique = true, updatable = false)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user")
    @GenericGenerator(
            name = "sequence_user",
            strategy = "com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "USER_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String userId;

    @Column(name = "userName", nullable = false, unique = true)
    @NotNull
    @Size(max = 40)
    @Pattern(regexp = Regex.REGEX_USERNAME)
    @Schema(example = "thanhbinh123")
    private String userName;

    @Column(name = "fullname", nullable = false, unique = true, updatable = false)
    @NotNull
    @Size(max = 50)
    @Pattern(regexp = Regex.REGEX_FULLNAME)
    @Schema(example = "Trương Thanh Bình")
    private String name;

    @Column(name = "birthDate")
    @Schema(example = "2000-12-13")
    private Date birthDate;

    @Column(name = "phoneNumber")
    @Size(max = 15)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @Column(name = "gender", nullable = false)
    @NotNull
    @Schema(example = "true")
    private boolean gender;

    @Column(name = "country")
    @Size(max = 50)
    private String country;

    @Column(name = "address")
    @Size(max = 50)
    private String address;

    @Column(name = "registeredAt", nullable = false)
    @Schema(example = "2020-12-01")
    private Date registeredAt;

    @Column(name = "lastLogin")
    @Schema(example = "2020-12-13")
    private Date lastLogin;

    @Column(name = "password", nullable = false)
    @Size(min = 10, max = 20)
    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "avatar")
    @JsonIgnore
    private String avatar;

    @Column(name = "status")
    @NotNull
    @Schema(example = "true")
    private boolean status;

    @Transient
    private String roleId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Role role;

    public String getRoleId() {
        return role != null ? role.getRoleId() : null;
    }
}
