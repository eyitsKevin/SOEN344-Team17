package com.soen344.ubersante;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/controller")
public class Controller {

    @GetMapping("/values")
    public String getValues(){
        return "Hello world this is the first input";
    }

}
