package com.library.libraryspringjpa.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class LoginRequestDTO {

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$", message = "Password need character and number, and need minimum eight character!")
    private String password;

    public LoginRequestDTO(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
