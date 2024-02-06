package com.email;

public record EmailAddress(String value){
    
    public static EmailAddress of(String email){
        return new EmailAddress(email);
    }

}
