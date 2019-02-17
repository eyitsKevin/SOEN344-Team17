package com.soen344.ubersante.dto;

import com.soen344.ubersante.validation.ValidAccessId;

import javax.validation.constraints.NotEmpty;

public class NurseLoginForm {

    @ValidAccessId
    private String accessId;

    @NotEmpty
    private String password;

    public String getAccessId() {
        return accessId;
    }

    public String getPassword() {
        return password;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
