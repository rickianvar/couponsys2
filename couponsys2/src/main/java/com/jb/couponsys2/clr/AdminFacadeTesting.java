package com.jb.couponsys2.clr;

import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.login.ClientType;
import com.jb.couponsys2.login.LoginManager;
import com.jb.couponsys2.repos.CompanyRepository;
import com.jb.couponsys2.services.AdminService;
import com.jb.couponsys2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class AdminFacadeTesting implements CommandLineRunner {

    private AdminService adminService;
    private final LoginManager loginManager;


    @Override
    public void run(String... args) throws Exception {

        Company company11 = Company.builder()
                .name("stimatsky")
                .email("stimatsky@gmail.com")
                .password("1234")
                .build();

        System.out.println(Art.ADMIN_SERVICE);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ LOGIN ADMIN SERVICE wrong pass !!!!  @@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService = (AdminService) loginManager.login("admin@admin.com", "ad", ClientType.ADMINISTRATOR);

        if (adminService == null) {
            System.out.println("bad login as admin");
        } else {
            System.out.println("Successful login as admin");
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@ LOGIN ADMIN FACAD SUCC !!!!      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        if (adminService == null) {
            System.out.println("bad login as admin");
        } else {
            System.out.println("Successful login as admin");
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@ ADD COMPANY 11 EXCEPTION        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //************
        try {
            adminService.addCompany(company11);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@ ADD COMPANY EXCEPTION        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        company11.setName("elit");
        company11.setEmail("stimatsky@gmail.com");

        try {
            adminService.addCompany(company11);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        company11.setEmail("elit@gmail.com");

        try {
            adminService.addCompany(company11);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@ ADD COMPANY                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ update  company  12 & show after @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(" update id 12 not exist - execptionnnn ");

        Company com1 = adminService.getSingleCompany(1);
        try {
            adminService.updateCompany(12, com1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(" update name  exception -  cannot update comp name  ");

        com1 = adminService.getSingleCompany(1);
        com1.setName("stimatsky");

        try {
            adminService.updateCompany(1, com1);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@  delete by id 4    @@@@@@@@@@@@@@@@@@@@@@@@@");

        adminService.deleteCompany(4);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ get all company    @@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ get company 1      @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(adminService.getSingleCompany(1));

        System.out.println("@@@@@@@@@@@@@@@@@@@@ add One customer   @@@@@@@@@@@@@@@@@@@@@@@@@");
        Customer customer6 = Customer.builder()
                .firstName("yona")
                .lastName("yakov")
                .email("rickianvar@gmail.com")
                .password("1234")
                .build();

        System.out.println("add CAUSE EXCEPTION CUS 6 ");

        try {
            adminService.addCustomer(customer6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        customer6.setEmail("yona@gmail.com");
        System.out.println("add SHOULD BE succcessfulll cus6 ");
        try {
            adminService.addCustomer(customer6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@ get all customers       @@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ update  CUSTOMER 12 & show after @@@@@@@@@@@@@@@@@");
        System.out.println(" update id 12 not exist - execptionnnn ");
        Customer cus2 = adminService.getSingleCustomer(2);

        try {
            adminService.updateCustomer(12, cus2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        customer6.setLastName("asida");
        try {
            adminService.updateCustomer(5, customer6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@ get all customers after upd @@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ delete customers 4          @@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService.deleteCustomer(4);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ get all customers after del @@@@@@@@@@@@@@@@@@@@@@@@@");
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("@@@@@@@@@@@@@@@@@@@@ get one customer            @@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(adminService.getSingleCustomer(1));


    }
}
