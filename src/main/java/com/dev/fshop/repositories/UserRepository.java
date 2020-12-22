package com.dev.fshop.repositories;

import com.dev.fshop.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountByUserName(String username);
}
