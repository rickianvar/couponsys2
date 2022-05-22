package com.jb.couponsys2.services;


import com.jb.couponsys2.bean.Company;
import com.jb.couponsys2.bean.Customer;
import com.jb.couponsys2.exceptions.SystemException;

import java.util.List;

public interface AdminService {

    void addCompany(Company company) throws SystemException;
    void updateCompany(int companyId , Company company) throws SystemException;
    void deleteCompany(int companyId);

    List<Company> getAllCompanies();
    Company getSingleCompany(int id) throws SystemException;

    void addCustomer(Customer customer) throws SystemException;
    void updateCustomer(int customerId, Customer customer) throws SystemException;
    void deleteCustomer(int customerId);

    List<Customer> getAllCustomers();
    Customer getSingleCustomer(int customerId) throws SystemException;

}
