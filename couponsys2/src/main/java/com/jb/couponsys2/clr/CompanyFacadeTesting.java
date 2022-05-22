package com.jb.couponsys2.clr;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.SystemException;
import com.jb.couponsys2.login.ClientType;
import com.jb.couponsys2.login.LoginManager;
import com.jb.couponsys2.repos.CompanyRepository;
import com.jb.couponsys2.services.AdminService;
import com.jb.couponsys2.services.CompanyService;
import com.jb.couponsys2.services.CompanyServiceImp;
import com.jb.couponsys2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(3)

public class CompanyFacadeTesting implements CommandLineRunner {

    private CompanyService companyService;
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {


        System.out.println(Art.COMPANY_SERVICE);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ LOGIN COMPANY SERVICE wrong pass !!!!  @@@@@@@@@@@@@@@@@@@@@");
        companyService = (CompanyServiceImp) loginManager.login("stimatsky@gmail.com", "ad", ClientType.COMPANY);

        if (companyService == null) {
            System.out.println("bad login as company");
        } else {
            System.out.println("Successful login as company");
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@ LOGIN company service SUCC !!!!     @@@@@@@@@@@@@@@@@@@@@@@@@");

        companyService = (CompanyServiceImp) loginManager.login("stimatsky@gmail.com", "1234", ClientType.COMPANY);
        if (companyService == null) {
            System.out.println("bad login as company");
        } else {
            System.out.println("Successful login as company");
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@ ADD COUPON  service        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        Coupon coupon1 = Coupon.builder()
                .title("stimatski")
                .description("50% discount for   book ")
                .category(Category.Books)
                .price(5)
                .amount(20)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(10)))
                .image("http://stim.com")
                .company(companyService.getSingleCompany(1))
                .build();

        System.out.println("add coupon same title EXCEPTION   ");
        try {
            companyService.addCoupon(1, coupon1);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }

        coupon1.setTitle("checkupd");
        System.out.println(" add coupon  comp 1   ");
        try {
            companyService.addCoupon(1, coupon1);
        } catch (SystemException e) {
            System.out.println(e.getMessage());

        }

        System.out.println(" add coupon for job EXPIRE - DELETE  ");
        Coupon coupondel = Coupon.builder()
                .title("win ")
                .description("50% discount for bottle ")
                .category(Category.Food)
                .price(80)
                .amount(20)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(10)))
                .image("http://win.com")
                .company(companyService.getSingleCompany(1))
                .build();

        try {
            companyService.addCoupon(1, coupondel);
        } catch (SystemException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@ COUPON LIST for COMP 1  @@@@@@@@@@@@@@@@@@@@@@@@@");
        companyService.getCompanyCoupons(1).forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ update  COUPON  service @@@@@@@@@@@@@@@@@@@@@@@@@");

        Coupon coupon2 = companyService.getSingleCoupon(2);
        System.out.println("wll be exception: coupon id 100 NOT found for update   ");

        try {
            companyService.updateCoupon(1, 100, coupon2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("will be exception:  company id  exist ");

        try {
            companyService.updateCoupon(100, 2, coupon2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("sucs update coupon2 title  ");
        coupon2.setTitle("ricki upd copon");
        try {
            companyService.updateCoupon(1, 2, coupon2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@ COUPON  list      @@@@@@@@@@@@@@@@@@@@@@@@@");
        companyService.getAllCoupons().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ AFTER DELETE COUPON list @@@@@@@@@@@@@@@@@@@");
        companyService.deleteCoupon(5, 5);
        companyService.getAllCoupons().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ COUPON LIST for COMP 1 AND CATEGORY @@@@@@@@@@");
        companyService.getCompanyCoupons(1, Category.Books).forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ COUPON LIST for COMP 1 MAXPRICE @@@@@@@@@@@@@@@");
        companyService.getCompanyCoupons(1, 20).forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ company detail        @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(companyService.getSingleCompany(1));


    }
}
