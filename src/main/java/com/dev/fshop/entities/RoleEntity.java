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
import java.util.Collection;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Role")
@Table(name = "Role")
public class RoleEntity {

    @Id
    @Column(name = "roleId", nullable = false, unique = true)
    private String roleId;
    @Column(name = "roleName")
    private String roleName;

    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<CustomerEntity> customerEntities;
}
