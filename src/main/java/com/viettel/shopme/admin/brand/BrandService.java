package com.viettel.shopme.admin.brand;

import com.viettel.shopme.common.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
public class BrandService {
    @Autowired
    private BrandRepository repo;

    public List<Brand> getAllBrand() {
        return (List<Brand>) repo.findAll();
    }

    public Brand get(Integer id) throws BrandNotFoundException {
        try {
            return repo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new BrandNotFoundException("Could not found any brand with ID " + id);
        }
    }

    public Brand save(Brand brand) {
        return repo.save(brand);
    }

    public void delete(Integer id) throws BrandNotFoundException {
        if(repo.countById(id) == null || repo.countById(id) < 1) {
            throw new BrandNotFoundException("Could not found the Brand with id: " + id);
        }
        repo.deleteById(id);
    }

    public String checkUnique(Integer id, String name) {
        boolean isCreatingNew = (id == null || id == 0);
        Brand brandByName = repo.findByName(name);

        if(isCreatingNew) {
            if (brandByName != null) return "Duplicate";
        } else {
            if (brandByName != null && !Objects.equals(brandByName.getId(), id)) {
                return "Duplicate";
            }
        }

        return "OK";
    }
}
