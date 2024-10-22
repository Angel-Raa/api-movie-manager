package com.github.angel.raa.utils.validator;

import com.github.angel.raa.excpetion.InvalidPasswordException;
import org.springframework.util.StringUtils;

public class PasswordValidator {
    /**
     * Validate password and password repeated
     * @param password
     * @param passwordRepeated
     */
    public static void validatePassword(String password, String passwordRepeated) {
        if(!StringUtils.hasText(password) || !StringUtils.hasText(passwordRepeated)){
            throw new IllegalArgumentException("Passwords must contains data");
        }
        if(!password.equals(passwordRepeated)){
            throw new InvalidPasswordException(password, passwordRepeated, "Passwords must be the same");
        }
        if(password.length() < 8){
            throw new InvalidPasswordException(password, passwordRepeated, "Password must be at least 8 characters long");
        }




    }


}
