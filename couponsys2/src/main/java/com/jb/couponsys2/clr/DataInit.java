package com.jb.couponsys2.clr;

import com.jb.couponsys2.bean.Category;
import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Coupon;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.SystemException;
import com.jb.couponsys2.repos.CompanyRepository;
import com.jb.couponsys2.repos.CouponRepository;
import com.jb.couponsys2.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Order(1)

public class DataInit implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        Company company1 = Company.builder()
                .name("stimatsky")
                .email("stimatsky@gmail.com")
                .password("1234")
                .build();

        Company company2 = Company.builder()
                .name("osem ")
                .email("osem@gmail.com")
                .password("1234")
                .build();

        Company company3 = Company.builder()
                .name("wili food ")
                .email("wilifood@gmail.com")
                .password("1234")
                .build();

        Company company4 = Company.builder()
                .name("shilav ")
                .email("shilav@gmail.com")
                .password("1234")
                .build();

        Company company5 = Company.builder()
                .name("flyfoot ")
                .email("flyfoot@gmail.com")
                .password("1234")
                .build();

        companyRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5));

        System.out.println("----------------company init table ---------------------");
        companyRepository.findAll().forEach(System.out::println);

        Customer customer1 = Customer.builder()
                .firstName("ricki")
                .lastName("anvar")
                .email("rickianvar@gmail.com")
                .password("1234")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("shoshi")
                .lastName("zecharia")
                .email("shoshizecha@gmail.com")
                .password("1234")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("daniel")
                .lastName("shriki")
                .email("danielshriki@gmail.com")
                .password("1234")
                .build();

        Customer customer4 = Customer.builder()
                .firstName("mor")
                .lastName("anvar")
                .email("rm.anvar@gmail.com")
                .password("1234")
                .build();

        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4));

        System.out.println("----------------customer init table ---------------------");
        customerRepository.findAll().forEach(System.out::println);

        Coupon coupon1 = Coupon.builder()
                .title("stimatski")
                .description("50% discount for travel book ")
                .category(Category.Books)
                .price(5)
                .amount(20)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(10)))
                .image("http://stim.com")
                .company(companyRepository.findById(1).get())
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("stimatski")
                .description("1 + 1  ")
                .category(Category.Books)
                .price(20)
                .amount(10)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(2)))
                .image("http://stim.com")
                .company(companyRepository.findById(1).get())
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("osem discount")
                .description("1 + 1  ")
                .category(Category.Food)
                .price(5)
                .amount(3)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(2)))
                .image("http://osem.com")
                .company(companyRepository.findById(2).get())
                .build();

        Coupon coupon4 = Coupon.builder()
                .title("shilav cloth discount")
                .description(" 1 + 1 for dress ")
                .category(Category.Fashion)
                .price(30)
                .amount(20)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(2)))
                .image("http://shilav.com")
                .company(companyRepository.findById(4).get())
                .build();

        Coupon coupon5 = Coupon.builder()
                .title("flyfoot discount")
                .description(" 1 + 1 SANDALS ")
                .category(Category.Fashion)
                .price(55)
                .amount(30)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(2)))
                .image("http://flyfoot.com")
                .company(companyRepository.findById(5).get())
                .build();

        Coupon coupon6 = Coupon.builder()
                .title(" tuna discount")
                .description("tuna for 2 shekel ")
                .category(Category.Food)
                .price(2)
                .amount(200)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://wilituna.com")
                .company(companyRepository.findById(3).get())
                .build();

        Coupon coupon7 = Coupon.builder()
                .title("CASTRO ")
                .description("50% discount for SHIRT ")
                .category(Category.Fashion)
                .price(80)
                .amount(20)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(10)))
                .image("http://castro.com")
                .company(companyRepository.findById(1).get())
                .build();


        couponRepository.saveAll(Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7));

        System.out.println("----------------coupons init list ---------------------");
        couponRepository.findAll().forEach(System.out::println);

        System.out.println("----------------company after coupon--------------------");
        companyRepository.findAll().forEach(System.out::println);

        couponRepository.addCouponPurchase(1, 1);
        couponRepository.addCouponPurchase(1, 2);
        couponRepository.addCouponPurchase(2, 1);
        couponRepository.addCouponPurchase(3, 1);
        couponRepository.addCouponPurchase(3, 4);
        couponRepository.addCouponPurchase(4, 1);
        couponRepository.addCouponPurchase(5, 5);
        couponRepository.addCouponPurchase(4, 5);
        couponRepository.addCouponPurchase(5, 6);
        couponRepository.addCouponPurchase(5, 7);


    }
}
