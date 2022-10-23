package com.yuriikoss.codingtask03sevenhillsgrouptech.exeption;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(String.format("User with id [%d] not found", userId));
    }
}
