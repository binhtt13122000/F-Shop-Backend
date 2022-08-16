package com.dev.fshop.repositories;

import com.dev.fshop.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Account, String> {
    Account findAccountByUserName(String username);

    boolean existsAccountByUserName(String username);

    boolean existsAccountByEmail(String email);

    @Query("select u from Account  u where (:status = 2 or u.status = :status) and (:email is null or u.email =: email) and (:role is null or u.role.roleName =: role)")
    Page<Account> searchAccountsByParameters(int status, String email, String role, Pageable pageable);

    @Query("select u from Account  u where (:q is null or u.name LIKE : q) or (:q is null or u.userId LIKE :q) or (:q is null or u.role.roleName LIKE : q)")
    Page<Account> searchAccountsByParameter(String q, Pageable pageable);


}
