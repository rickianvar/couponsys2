package com.jb.couponsys2.services;

import com.jb.couponsys2.repos.CompanyRepository;
import com.jb.couponsys2.repos.CouponRepository;
import com.jb.couponsys2.repos.CustomerRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@NoArgsConstructor

public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    public abstract boolean login(String email, String password);

}
