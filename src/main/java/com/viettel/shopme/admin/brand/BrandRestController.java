package com.viettel.shopme.admin.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ShopmeAdmin")
public class BrandRestController {
    @Autowired
    private BrandService service;

    @PostMapping("/brands/check_unique")
    public String checkUnique(@Param("id") Integer id,
                              @Param("name") String name
    ) {
        return service.checkUnique(id, name);
    }
}
