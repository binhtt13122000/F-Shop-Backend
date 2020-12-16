package com.dev.fshop.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private String userId;

    @Column(name = "roleId")
    private int roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "registeredAt")
    private Date registeredAt;

    @Column(name = "lastLogin")
    private  Date lastLogin;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "CommentEntity", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "OrderDetailEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetailEntity> orderDetailEntities;

    @OneToMany(mappedBy = "PromotionEntity", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<PromotionEntity> promotionEntities;

    @ManyToOne
    @JoinColumn(name = "roleId") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private RoleEntity roleEntity;
}
