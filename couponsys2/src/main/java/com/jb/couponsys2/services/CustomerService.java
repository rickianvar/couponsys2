package com.jb.couponsys2.services;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.SystemException;

import java.util.List;

public interface CustomerService {

    void purchaseCoupon(int customerId, Coupon coupon) throws SystemException;

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCoupons(int customerId, Category category);

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice);

    Customer getSingleCustomer(int customerId) throws SystemException;


}
