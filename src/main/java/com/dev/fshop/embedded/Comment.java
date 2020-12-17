package com.dev.fshop.embedded;

import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @Column(name = "commentId", nullable = false, unique = true)
    private String commentId;
    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "proId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductEntity productEntity;
}
