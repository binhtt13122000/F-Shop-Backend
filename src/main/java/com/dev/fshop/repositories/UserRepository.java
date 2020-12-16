package com.dev.fshop.repositories;

import com.dev.fshop.entity.CustomerEntity;
import com.dev.fshop.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query("Select u from CustomerEntity u where u.userId = :userId")
    public CustomerEntity checkUserExist(String userId);

    @Query("Select u from CustomerEntity u where u.name LIKE :name")
    public List<CustomerEntity> searchStudentByName(String name);

    @Query("Select u from CustomerEntity u where u.userId = :userId and u.password = :password")
    public CustomerEntity checkLoginById(Integer userId, String password);


    @Modifying(clearAutomatically = true)
    @Query("UPDATE CustomerEntity u set u.status = 0 where u.userId = :userId")
    public CustomerEntity deleteAccount(Integer userId);

    @Transactional
    public CustomerEntity insertWithEntityManager(CustomerEntity customerEntity);

    public CustomerEntity findByName(String name);
}
