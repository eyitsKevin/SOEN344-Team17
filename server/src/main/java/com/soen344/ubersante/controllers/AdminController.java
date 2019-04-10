package com.soen344.ubersante.controllers;

import com.soen344.ubersante.dto.LoginForm;
import com.soen344.ubersante.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginAdmin(@Valid @RequestBody final LoginForm loginForm) {

        boolean validLogin = adminService.validateLogin(loginForm);

        return validLogin ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

    }


}
