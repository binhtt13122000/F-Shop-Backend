package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Role")
public class RoleEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;
    @Column(name = "roleName")
    private String roleName;

    @OneToMany(mappedBy = "CustomerEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CustomerEntity> customerEntities;
}
