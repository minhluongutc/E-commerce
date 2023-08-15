package com.viettel.shopme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.viettel.shopme.common.entity", "com.viettel.shopme.admin.user"})
public class ShopmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopmeApplication.class, args);
    }

}
