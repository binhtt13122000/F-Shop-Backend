package com.dev.fshop.entities;


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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Role")
@Table(name = "Role")
public class Role implements Serializable {
    //id
    @Id
    @Column(name = "roleId", nullable = false, unique = true, updatable = false)
    @NotNull
    @NotBlank
    @Size(max = 10)
    @Schema(example = "ROL_1")
    private String roleId;
    //name
    @Column(name = "roleName")
    @Schema(example = "CUSTOMER")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String roleName;

}
