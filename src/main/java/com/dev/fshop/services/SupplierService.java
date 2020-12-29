package com.dev.fshop.services;

import com.dev.fshop.entities.Supplier;
import org.springframework.stereotype.Service;

public interface SupplierService {
    public Supplier findSupplierBySupplierId(String supplierId);
}
