package com.dev.fshop.validation.existIn;

import com.dev.fshop.repositories.CategoryRepository;
import com.dev.fshop.repositories.ProductRepository;
import com.dev.fshop.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistInServiceImp implements ExistInService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public boolean existIn(String fieldName, String fieldValue, String className) {
        if (fieldValue == null) {
            return false;
        }
        if (className.equals("Product")) {
            switch (fieldName) {
                case "categoryId":
                    return categoryRepository.existsCategoryByProTypeId(fieldValue);
                case "supplierId":
                    return supplierRepository.existsSupplierBySupplierId(fieldValue);
                default:
                    throw new UnsupportedOperationException("Field name not supported");
            }
        }

        throw new UnsupportedOperationException("Class not supported");
    }
}
