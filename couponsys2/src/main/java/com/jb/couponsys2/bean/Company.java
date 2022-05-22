package com.jb.couponsys2.bean;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "company")
    @Singular
    private List<Coupon> coupons ;

}
