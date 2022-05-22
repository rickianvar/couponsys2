package com.jb.couponsys2.clr;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.exceptions.SystemException;
import com.jb.couponsys2.login.ClientType;
import com.jb.couponsys2.login.LoginManager;
import com.jb.couponsys2.services.CompanyService;
import com.jb.couponsys2.services.CompanyServiceImp;
import com.jb.couponsys2.services.CustomerService;
import com.jb.couponsys2.services.CustomerServiceImp;
import com.jb.couponsys2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(4)

public class CustomerFacadeTesting implements CommandLineRunner {

    private CustomerService customerService;
    private final CompanyService companyService;
    private final LoginManager loginManager;


    @Override
    public void run(String... args) throws Exception {

        System.out.println(Art.CUSTOMER_SERVICE);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ LOGIN CUSTOMER SERVICE wrong pass !!!!  @@@@@@@@@@@@@@@@@");
        customerService = (CustomerServiceImp) loginManager.login("stimatsky@gmail.com", "ad", ClientType.CUSTOMER);

        if (customerService == null) {
            System.out.println("bad login as customer");
        } else {
            System.out.println("Successful login as customer");
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@ LOGIN customer service SUCC !!!!  @@@@@@@@@@@@@@@@@@@@@@@@@");

        customerService = (CustomerServiceImp) loginManager.login("rickianvar@gmail.com", "1234", ClientType.CUSTOMER);
        if (customerService == null) {
            System.out.println("bad login as customer");
        } else {
            System.out.println("Successful login as customer");
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@ buy coupon   1  @@@@@@@@@@@@@@@@@@@@@@@@@");
        Coupon couponDB = companyService.getSingleCoupon(1);

        System.out.println("purchase coupon 1 exist  ");
        try {
            customerService.purchaseCoupon(1, couponDB);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("purchase coupon 1 for cust 5  , set amount=0");
        System.out.println("--------BEFORE  coupon purchase ------");
        System.out.println(companyService.getSingleCoupon(1));

        couponDB.setAmount(0);
        companyService.updateCoupon(1, 1, couponDB);
        try {
            customerService.purchaseCoupon(5, couponDB);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("purchase coupon expiration date ");
        couponDB.setAmount(20);
        couponDB.setEndDate(Date.valueOf(LocalDate.now().minusDays(12)));
        companyService.updateCoupon(1, 1, couponDB);
        try {
            customerService.purchaseCoupon(5, couponDB);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("  coupon and purchase coupon update amount -1 ");
        couponDB.setEndDate(Date.valueOf(LocalDate.now().plusDays(12)));
        companyService.updateCoupon(1, 1, couponDB);
        try {
            customerService.purchaseCoupon(5, couponDB);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------after  coupon purchase ------");
        System.out.println(companyService.getSingleCoupon(1));

        System.out.println("-----------get cust 5 coupon--------------------------");
        customerService.getCustomerCoupons(5).forEach(System.out::println);

        System.out.println("-----------get cust 5 category food - all coupon--------");
        customerService.getCustomerCoupons(5, Category.Food).forEach(System.out::println);

        System.out.println("-----------get cust 5 max price - all coupon--------");
        customerService.getCustomerCoupons(5, 6).forEach(System.out::println);

        System.out.println("-----------get cust 5 details--------");
        System.out.println(customerService.getSingleCustomer(5));

    }
}
