package com.dev.fshop.repositories;

import com.dev.fshop.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    boolean existsSupplierBySupplierId(String id);

    @Query("select u from Supplier  u where  u.supplierName like :supplierName")
    public List<Supplier> checkSupplierBySupplierName(String supplierName);

    @Query("select u from Supplier  u where (:supplierName is null) or (u.supplierName like :supplierName)")
    public Page<Supplier> searchSupplierBySupplierName(String supplierName, Pageable pageable);
}
