package com.jb.couponsys2.services;

import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.ErrMsg;
import com.jb.couponsys2.exceptions.SystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AdminServiceImp extends ClientService implements AdminService {

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    @Override
    public void addCompany(Company company) throws SystemException {

        if (this.companyRepository.existsByName(company.getName())) {
            throw new SystemException(ErrMsg.COMPANY_NAME_EXISTS);
        }
        if (this.companyRepository.existsByEmail(company.getEmail())) {
            throw new SystemException(ErrMsg.COMPANY_EMAIL_EXISTS);
        }
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws SystemException {

        Company inputCompany = this.companyRepository.findById(companyId).orElseThrow(() -> new SystemException(ErrMsg.COMPANY_ID_NOT_EXISTS));

        if (!inputCompany.getName().equals(company.getName())) {
            throw new SystemException(ErrMsg.COMPANY_NAME_EXISTS);
        }
        this.companyRepository.saveAndFlush(company);

    }

    @Override
    public void deleteCompany(int companyId) {
        this.couponRepository.deleteCouponPurchase(companyId);
        this.companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int id) throws SystemException {
        return this.companyRepository.findById(id).orElseThrow(() -> new SystemException(ErrMsg.COMPANY_ID_NOT_EXISTS));
    }

    @Override
    public void addCustomer(Customer customer) throws SystemException {
        if (this.customerRepository.existsByEmail(customer.getEmail())) {
            throw new SystemException(ErrMsg.EMAIL_EXISTS);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws SystemException {
        this.customerRepository.findById(customerId).orElseThrow(() -> new SystemException(ErrMsg.CUSTOMER_ID_NOT_EXISTS));
        this.customerRepository.saveAndFlush(customer);
    }


    @Override
    public void deleteCustomer(int customerId) {
        this.customerRepository.deleteById(customerId);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws SystemException {
        return this.customerRepository.findById(customerId).orElseThrow(() -> new SystemException(ErrMsg.CUSTOMER_ID_NOT_EXISTS));
    }

}
