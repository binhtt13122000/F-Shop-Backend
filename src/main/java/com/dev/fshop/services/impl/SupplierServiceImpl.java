package com.dev.fshop.services.impl;

import com.dev.fshop.entities.Supplier;
import com.dev.fshop.repositories.SupplierRepository;
import com.dev.fshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier findSupplierBySupplierId(String supplierId) {
        return supplierRepository.findById(supplierId).orElse(null);
    }
}
