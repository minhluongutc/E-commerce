package com.viettel.shopme.admin;

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

    @GetMapping("/login")
    public String viewLoginPage() {
        return "/login";
    }
}
