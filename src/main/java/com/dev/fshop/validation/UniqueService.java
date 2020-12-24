package com.dev.fshop.validation;


public interface UniqueService {
    boolean fieldValueExists(String fieldName, String fieldValue, String className) throws UnsupportedOperationException;
}
