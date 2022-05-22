package com.jb.couponsys2.repos;

import com.jb.couponsys2.bean.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmailAndPassword(String email, String password);

}
