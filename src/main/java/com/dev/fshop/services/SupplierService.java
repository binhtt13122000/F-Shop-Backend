package com.dev.fshop.services;

import com.dev.fshop.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SupplierService {
    public Supplier findSupplierBySupplierId(String supplierId);

    public List<Supplier> checkSupplierBySupplierName(String supplierName);

    public Supplier createNewSupplier(Supplier supplier);

    public Page<Supplier> searchSupplierBySupplierName(String supplierName, Pageable pageable);
}
