package com.viettel.shopme.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ShopmeAdmin")
public class AdminController {

    @GetMapping
    public String ShopmeAdmin() {
        return "/index";
    }
}
