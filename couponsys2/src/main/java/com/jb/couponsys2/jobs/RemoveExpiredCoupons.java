package com.jb.couponsys2.jobs;


import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class RemoveExpiredCoupons {

    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void exppc() {
        System.out.println("\u001B[35m" + "!!!!!!!DAILY JOB - BEFORE deletion of expired coupons " + "\033[0m");
        couponRepository.findAll().forEach(System.out::println);

        List<Coupon> expiredCoupons = couponRepository.findByEndDateLessThan(Date.valueOf(LocalDate.now()));

        System.out.println("\u001B[35m" + "Daily job coupon deleted coupons: ");

        for (Coupon coupon : expiredCoupons) {

            System.out.println(" coupons :" + coupon + "\n");
            couponRepository.deleteCouponPurchaseByCouponId(coupon.getId());
        }

        System.out.println("\u001B[35m" + "Daily job -COUPON LIST AFTER DELETE EXPIRED COUPONS " + "\033[0m");
        couponRepository.deleteByEndDateLessThan(Date.valueOf(LocalDate.now()));
        couponRepository.findAll().forEach(System.out::println);

    }

}
