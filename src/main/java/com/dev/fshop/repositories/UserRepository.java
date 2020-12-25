package com.dev.fshop.repositories;

import com.dev.fshop.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Account, String> {
    Account findAccountByUserName(String username);
    boolean existsAccountByUserName(String username);
    boolean existsAccountByEmail(String email);

    @Query("select u from Account  u where (:email is null or u.email =: email) and (:role is null or u.role.roleName =: role)")
    List<Account> searchAccountsByParameters(String email, String role);

    @Query("select u from Account  u where (:q is null or u.email LIKE : q) or (:q is null or u.role.roleName LIKE : q)")
    List<Account> searchAccountsByParameter(String q);

}
