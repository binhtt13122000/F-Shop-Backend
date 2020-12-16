package com.dev.fshop.repositories;

import com.dev.fshop.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomerEntity, Integer> {
    public CustomerEntity findByName(String name);
    public boolean deleteCustomerByName(String name);


    @Query("Select u from CustomerEntity u where u.userId = :userId and u.password = :password")
    public boolean checkUserExist(Integer userId, String password);

    @Query("Select u from CustomerEntity u where u.name LIKE :name")
    public CustomerEntity searchStudentByName(String name);

    @Query("Select u from CustomerEntity u where u.userId = :userId and u.password = :password")
    public CustomerEntity checkLoginById(Integer userId, String password);


}
