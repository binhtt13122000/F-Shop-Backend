package com.dev.fshop.repositories;

import com.dev.fshop.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Account, String> {

//    @Query("select u from Account u where u.userId = :userId")
//    public Account checkUserExist(String userId);
//
//    public List<Account> findByName(String name);
//
//    @Query("select u from Account u where u.userId = :userId and u.password = :password")
//    public Account checkLoginByIdPassword(String userId, String password);
//
//    @Query("update Account  u set u.password = :password where  u.userId = :userId")
//    public Account updatePassword(String userId, String password);


    //public CustomerEntity updateProfile(CustomerEntity customerEntity);
//    @Transactional
//    public CustomerEntity insertCustomerWithEntityManager(CustomerEntity customerEntity);




}
