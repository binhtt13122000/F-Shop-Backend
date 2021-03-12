package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Supplier;
import com.dev.fshop.repositories.SupplierRepository;
import com.dev.fshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier findSupplierBySupplierId(String supplierId) {
        return supplierRepository.findById(supplierId).orElse(null);
    }

    @Override
    public List<Supplier> searchSupplierBySupplierName(String supplierName) {
        supplierName = supplierName + "-%";
        return supplierRepository.searchSupplierBySupplierName(supplierName);
    }

    @Override
    public Supplier createNewSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
}
