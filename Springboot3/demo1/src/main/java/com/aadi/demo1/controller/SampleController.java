package com.aadi.demo1.controller;

import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class SampleController {

    @InitBinder
    protected void initBinder(DataBinder binder) {
        binder.registerCustomEditor(String.class, "firstName", new FirstNamePropertyEditor());
        binder.registerCustomEditor(String.class, "lastName", new LastNamePropertyEditor());
    }

    @GetMapping(path = "/fetchUser")
    public String getUserDetails(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "age") Integer age) {
        return "fetching and returning user details based on firstName: " + firstName + ", lastName: " + lastName
                + ". age is: " + age;
    }
}
