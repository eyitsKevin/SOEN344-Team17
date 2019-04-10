package com.soen344.ubersante.dto;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
