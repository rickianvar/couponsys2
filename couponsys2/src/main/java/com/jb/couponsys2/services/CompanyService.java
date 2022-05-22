package com.jb.couponsys2.services;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.SystemException;

import java.util.List;

public interface CompanyService {


    void addCoupon(int companyId, Coupon coupon) throws SystemException;

    void updateCoupon(int companyId, int couponId , Coupon coupon) throws SystemException;

    void deleteCoupon(int companyId, int couponId);

    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getCompanyCoupons(int companyId, Category category);

    List<Coupon> getCompanyCoupons(int companyId,  double maxPrice);

    Company getSingleCompany(int companyId) throws SystemException;

    Coupon getSingleCoupon(int couponId) throws SystemException;

    List<Coupon> getAllCoupons();


}
