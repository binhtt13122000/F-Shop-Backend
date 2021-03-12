package com.dev.fshop.services;

import com.dev.fshop.entities.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SupplierService {
    public Supplier findSupplierBySupplierId(String supplierId);

    public List<Supplier> searchSupplierBySupplierName(String supplierName);

    public Supplier createNewSupplier(Supplier supplier);
}
