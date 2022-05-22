package com.jb.couponsys2.services;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.exceptions.ErrMsg;
import com.jb.couponsys2.exceptions.SystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp extends ClientService implements CompanyService {
    @Override
    public boolean login(String email, String password) {
        return companyRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws SystemException {
        if (couponRepository.existsByCompanyIdAndTitle(companyId, coupon.getTitle())) {
            throw new SystemException(ErrMsg.COUPON_EXIST);
        }
        this.couponRepository.save(coupon);

    }

    @Override
    public void updateCoupon(int companyId, int couponId, Coupon coupon) throws SystemException {


        Coupon inputCoupon = this.couponRepository.findById(couponId).orElseThrow(() -> new SystemException(ErrMsg.COUPON_ID_NOT_EXISTS));

        if (inputCoupon.getCompany().getId() != companyId) {
            throw new SystemException(ErrMsg.COUPON_NO_UPDATE_COMPANY);
        }
        this.couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int companyId, int couponId) {
        this.couponRepository.deleteCouponPurchaseByCouponId(couponId);
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) {
        return couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) {
        return couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getSingleCompany(int companyId) throws SystemException {
        return this.companyRepository.findById(companyId).orElseThrow(() -> new SystemException(ErrMsg.COMPANY_ID_NOT_EXISTS));
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws SystemException {
        return this.couponRepository.findById(couponId).orElseThrow(() -> new SystemException(ErrMsg.COUPON_ID_NOT_EXISTS));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return this.couponRepository.findAll();
    }
}
