package com.jb.couponsys2.repos;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `spring-coupon-system-2-bhp`.`customers_coupons` (`customer_id`, `coupons_id`) VALUES (? , ?);", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `spring-coupon-system-2-bhp`.`customers_coupons` where customer_id <> 0 and COUPONS_ID in" +
            "( select id  from `spring-coupon-system-2-bhp`.`coupons` where COMPANY_ID =?) "
            , nativeQuery = true)
    void deleteCouponPurchase(int companyId);

    // " delete from couponsys.customers_vs_coupons where customer_id <> 0 and COUPON_ID in ( select id  from couponsys.coupons where COMPANY_ID =?)

    boolean existsByCompanyIdAndTitle(int companyId, String title);

    List<Coupon> findByCompanyId(int companyId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `spring-coupon-system-2-bhp`.`customers_coupons` where customer_id <> 0  and COUPONS_ID = ? ;"
            , nativeQuery = true)
    void deleteCouponPurchaseByCouponId(int couponId);

    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double maxPrice);

    @Query(value = "select exists(SELECT * FROM `spring-coupon-system-2-bhp`.`customers_coupons` where customer_id = ?  AND COUPONS_ID = ? ) as res ;"
            , nativeQuery = true)
    long ExistCouponPurchaseByCustIdAndCouponId(int customerId, int couponId);

    @Query(value = "SELECT *  FROM `spring-coupon-system-2-bhp`.coupons where id in ( SELECT COUPONS_ID FROM `spring-coupon-system-2-bhp`.customers_coupons  where CUSTOMER_ID = ?);"
            , nativeQuery = true)
    List<Coupon> getCustomerAllCoupons(int customerId);

    @Query(value = "SELECT *  FROM `spring-coupon-system-2-bhp`.coupons where CATEGORY = ? and id in ( SELECT COUPONS_ID FROM `spring-coupon-system-2-bhp`.customers_coupons  where CUSTOMER_ID = ?);"
            , nativeQuery = true)
    List<Coupon> getCustomerAllCouponsByCategory(int categoryId, int customerId);

    @Query(value = "SELECT *  FROM `spring-coupon-system-2-bhp`.coupons where price <= ? and id in ( SELECT COUPONS_ID FROM `spring-coupon-system-2-bhp`.customers_coupons  where CUSTOMER_ID = ?);"
            , nativeQuery = true)
    List<Coupon> getCustomerAllCouponsByMaxPrice(double maxPrice, int customerId);

    @Transactional
    @Modifying
    void deleteByEndDateLessThan(Date currDate);

    List<Coupon> findByEndDateLessThan(Date currDate);

}
