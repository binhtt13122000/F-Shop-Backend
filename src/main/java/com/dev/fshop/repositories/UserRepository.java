package com.dev.fshop.repositories;

import com.dev.fshop.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<CustomerEntity, String> {

//    public CustomerEntity checkUserExist(String userId);
//    public List<CustomerEntity> findByCustomerName(String name);
//    @Query("select u from CustomerEntity u where u.userId = :userId and u.password = :password")
//    public CustomerEntity checkLoginByIdPassword(String userId, String password);
//    public List<CustomerEntity>  findCustomerByRole(String roleId);
//    public CustomerEntity deleteAccount(String userId);
//    public CustomerEntity updatePassword(String userId, String password);
//    public CustomerEntity updateProfile(CustomerEntity customerEntity);
//    @Transactional
//    public CustomerEntity insertCustomerWithEntityManager(CustomerEntity customerEntity);




}
