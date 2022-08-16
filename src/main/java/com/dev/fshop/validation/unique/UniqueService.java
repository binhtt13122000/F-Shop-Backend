package com.dev.fshop.validation.unique;


public interface UniqueService {
    boolean fieldValueExists(String fieldName, String fieldValue, String className) throws UnsupportedOperationException;
}
