package com.dev.fshop.repositories;

import com.dev.fshop.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<CustomerEntity, String> {

    @Query("select u from CustomerEntity u where u.userId = :userId")
    public CustomerEntity checkUserExist(String userId);

    public List<CustomerEntity> findByName(String name);

    @Query("select u from CustomerEntity u where u.userId = :userId and u.password = :password")
    public CustomerEntity checkLoginByIdPassword(String userId, String password);

    @Query("update CustomerEntity  u set u.password = :password where  u.userId = :userId")
    public CustomerEntity updatePassword(String userId, String password);


    //public CustomerEntity updateProfile(CustomerEntity customerEntity);
//    @Transactional
//    public CustomerEntity insertCustomerWithEntityManager(CustomerEntity customerEntity);




}
