package com.jb.couponsys2.login;

import com.jb.couponsys2.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class LoginManager {

    private final ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) {
        switch (clientType) {
            case ADMINISTRATOR:
                AdminService adminService = ctx.getBean(AdminService.class);

                if (((AdminServiceImp) adminService).login(email, password)) {
                    return (AdminServiceImp) adminService;
                }
                break;

            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);

                if (((CompanyServiceImp) companyService).login(email, password)) {
                    return (CompanyServiceImp) companyService;
                }
                break;

            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (((CustomerServiceImp) customerService).login(email, password)) {
                    return (CustomerServiceImp) customerService;
                }
                break;
            default:
                break;
        }

        return null;
    }

}
