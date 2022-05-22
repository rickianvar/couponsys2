package com.jb.couponsys2.services;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.ErrMsg;
import com.jb.couponsys2.exceptions.SystemException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImp extends ClientService implements CustomerService {
    @Override
    public boolean login(String email, String password) {
        return customerRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void purchaseCoupon(int customerId, Coupon coupon) throws SystemException {

        Coupon couponInp = couponRepository.getById(coupon.getId());

        if (couponRepository.ExistCouponPurchaseByCustIdAndCouponId(customerId, coupon.getId()) == 1) {
            throw new SystemException(ErrMsg.COUPON_EXIST);
        }

        if (couponInp.getAmount() == 0) {
            throw new SystemException(ErrMsg.COUPON_AMOUNT);
        }

        if (couponInp.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new SystemException(ErrMsg.COUPON_EXPIRED);
        }

        couponRepository.addCouponPurchase(customerId, coupon.getId());

        couponInp.setAmount(coupon.getAmount() - 1);
        couponRepository.saveAndFlush(couponInp);

    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        return couponRepository.getCustomerAllCoupons(customerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, Category category) {
        return couponRepository.getCustomerAllCouponsByCategory(category.ordinal(), customerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) {
        return couponRepository.getCustomerAllCouponsByMaxPrice(maxPrice, customerId);
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws SystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new SystemException(ErrMsg.CUSTOMER_ID_NOT_EXISTS));
    }

}
