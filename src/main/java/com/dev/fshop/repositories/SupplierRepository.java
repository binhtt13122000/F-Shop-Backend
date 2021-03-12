package com.dev.fshop.repositories;

import com.dev.fshop.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    boolean existsSupplierBySupplierId(String id);

    @Query("select u from Supplier  u where  u.supplierName like :supplierName")
    public List<Supplier> searchSupplierBySupplierName(String supplierName);
}
