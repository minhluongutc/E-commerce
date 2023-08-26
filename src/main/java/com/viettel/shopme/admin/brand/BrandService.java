package com.viettel.shopme.admin.brand;

import com.viettel.shopme.common.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService {
    @Autowired
    private BrandRepository repo;

    public List<Brand> getAllBrand() {
        return (List<Brand>) repo.findAll();
    }
}
