package com.dev.fshop.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private Collection<CustomerEntity> customerEntities;
}
