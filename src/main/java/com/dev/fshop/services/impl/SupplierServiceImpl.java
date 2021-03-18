package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Supplier;
import com.dev.fshop.repositories.SupplierRepository;
import com.dev.fshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<Supplier> checkSupplierBySupplierName(String supplierName) {
        supplierName = supplierName + "-%";
        return supplierRepository.checkSupplierBySupplierName(supplierName);
    }

    @Override
    public Supplier createNewSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Page<Supplier> searchSupplierBySupplierName(String supplierName, Pageable pageable) {
        if(supplierName != null) {
            supplierName = "%" + supplierName + "%";
        }
        return supplierRepository.searchSupplierBySupplierName(supplierName, pageable);
    }
}
