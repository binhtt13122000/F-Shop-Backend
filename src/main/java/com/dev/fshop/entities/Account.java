package com.dev.fshop.entities;


import com.dev.fshop.generator.entities.StringPrefixedSequenceIdGenerator;
import com.dev.fshop.utils.Regex;
import com.dev.fshop.validation.unique.Unique;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "userId", nullable = false, unique = true, updatable = false)
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
    @Unique(fieldName = "userName", className = "Account", message = "Username is taken!")
    @NotNull(message = "userName is not null!")
    @Size(max = 40, min = 10, message = "Username must have between 10 and 40 characters!")
    @Pattern(regexp = Regex.REGEX_USERNAME, message = "Username does not contain special character!")
    @Schema(example = "thanhbinh123")
    private String userName;

    @Column(name = "fullname", nullable = false, unique = true, updatable = false)
    @NotNull(message = "Fullname is not null!")
    @Size(max = 50, message = "Max Size of Fullname is 50 characters!")
    @Pattern(regexp = Regex.REGEX_FULLNAME, message = "Format of fullname is wrong!")
    @Schema(example = "Trương Thanh Bình")
    private String name;

    @Column(name = "birthDate")
    @Schema(example = "2000-12-13")
    private Date birthDate;

    @Column(name = "phoneNumber")
    @Pattern(regexp = Regex.REGEX_PHONE, message = "Phone is not valid!")
    @Schema(example = "0902472118")
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    @Unique(fieldName = "email", className = "Account", message = "Email is taken!")
    @NotNull(message = "Email is not null!")
    @Email(message = "Email is not valid!")
    @Size(max = 50, min = 10, message = "Email must have between 10 and 40 characters!")
    @Schema(example = "binhtt13122000@gmail.com")
    private String email;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Gender is not null!")
    @Schema(example = "true")
    private boolean gender;

    @Column(name = "country")
    @Size(max = 50, message = "Country name has max length is 50!")
    @Schema(example = "Việt Nam")
    private String country;

    @Column(name = "address")
    @Size(max = 50, message = "Address name has max length is 50!")
    @Schema(example = "Q9, TP HCM")
    private String address;

    @Column(name = "registeredAt", nullable = false)
    @Schema(example = "2020-12-01")
    private Date registeredAt;

    @Column(name = "lastLogin")
    @Schema(example = "2020-12-13")
    private Date lastLogin;

    @Column(name = "password", nullable = false)
    @NotNull(message = "password is not null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    @Schema(example = "true")
    private int status;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String roleId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    @JsonIgnore
    private Role role;

    public String getRoleId() {
        return role != null ? role.getRoleId() : null;
    }

    public Account(@NotNull(message = "userName is not null!") @Size(max = 40, min = 10, message = "Username must have between 10 and 40 characters!") @Pattern(regexp = Regex.REGEX_USERNAME, message = "Username does not contain special character!") String userName, @NotNull(message = "Fullname is not null!") @Size(max = 50, message = "Max Size of Fullname is 50 characters!") @Pattern(regexp = Regex.REGEX_FULLNAME, message = "Format of fullname is wrong!") String name, Date birthDate, @Pattern(regexp = Regex.REGEX_PHONE, message = "Phone is not valid!") String phoneNumber, @NotNull(message = "Email is not null!") @Email(message = "Email is not valid!") @Size(max = 50, min = 10, message = "Email must have between 10 and 40 characters!") String email, @NotNull(message = "Gender is not null!") boolean gender, @Size(max = 50, message = "Country name has max length is 50!") String country, @Size(max = 50, message = "Address name has max length is 50!") String address, Date registeredAt, Date lastLogin, @NotNull(message = "password is not null") String password, String avatar, int status) {
        this.userName = userName;
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.address = address;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
        this.password = password;
        this.avatar = avatar;
        this.status = status;
    }
}
