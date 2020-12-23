package com.dev.fshop.entities;


import com.dev.fshop.generator.entites.StringPrefixedSequenceIdGenerator;
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
    //id
    @Id
    @Column(name = "userId", nullable = false, unique = true, updatable = false)
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user")
    @GenericGenerator(
            name = "sequence_user",
            strategy = "com.dev.fshop.generator.enti.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "USER_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            }
    )
    private String userId;

    //username
    @Column(name = "userName", nullable = false)
    @NotNull
    @NotBlank
    @Size(max = 40)
    @Pattern(regexp = Regex.REGEX_USERNAME)
    @Schema(example = "thanhbinh123")
    private String userName;

    //fullname
    @Column(name = "fullname", nullable = false, unique = true, updatable = false)
    @NotNull
    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = Regex.REGEX_FULLNAME)
    @Schema(example = "Trương Thanh Bình")
    private String name;

    //birthDate
    @NotBlank
    @Column(name = "birthDate")
    @Schema(example = "2000-12-13")
    private Date birthDate;

    //phone number
    @Column(name = "phoneNumber")
    @NotBlank
    @Size(max = 15)
    private String phoneNumber;

    //email
    @Column(name = "email", nullable = false, unique = true, updatable = false)
    @NotBlank
    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    //gender
    @Column(name = "gender", nullable = false)
    @NotBlank
    @NotNull
    @Schema(example = "true")
    private boolean gender;

    //country
    @Column(name = "country")
    @NotBlank
    @Size(max = 50)
    private String country;

    //address
    @Column(name = "address")
    @NotBlank
    @Size(max = 50)
    private String address;

    //registerAt
    @Column(name = "registeredAt", nullable = false)
    @NotNull
    @NotBlank
    @Schema(example = "2020-12-01")
    private Date registeredAt;


    //last login
    @Column(name = "lastLogin")
    @NotBlank
    @Schema(example = "2020-12-13")
    private  Date lastLogin;

    //password
    @Column(name = "password", nullable = false)
    @Size(min = 10, max = 20)
    @NotNull
    @NotBlank
    @JsonIgnore
    private String password;


    //avatar
    @Column(name = "avatar")
    @NotBlank
    @JsonIgnore
    private String avatar;

    //status
    @Column(name = "status")
    @NotNull
    @NotBlank
    @Schema(example = "true")
    private boolean status;

    //role
    @Transient
    @NotBlank
    private String roleId;

    public String getRoleId() {
        return role != null ? role.getRoleId() : null;
    }

    @ManyToOne
    @JoinColumn(name = "roleId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Role role;
}
