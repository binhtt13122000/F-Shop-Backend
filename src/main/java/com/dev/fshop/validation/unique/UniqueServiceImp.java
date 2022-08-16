package com.dev.fshop.validation.unique;

import com.dev.fshop.repositories.UserRepository;
import com.dev.fshop.validation.unique.UniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueServiceImp implements UniqueService {
    @Autowired
    private UserRepository userRepository;


    public boolean fieldValueExists(String fieldName, String fieldValue, String className) throws UnsupportedOperationException {
        if(fieldValue == null){
            return false;
        }
        if(className.equals("Account")){
            switch (fieldName){
                case "userName":
                    return userRepository.existsAccountByUserName(fieldValue);
                case "email":
                    return userRepository.existsAccountByEmail(fieldValue);
                default:
                    throw new UnsupportedOperationException("Field name not supported");
            }
        }

        throw new UnsupportedOperationException("Class not supported");
    }
}
