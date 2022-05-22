package com.jb.couponsys2.repos;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    }
