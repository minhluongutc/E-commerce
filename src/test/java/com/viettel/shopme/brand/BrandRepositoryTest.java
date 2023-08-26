package com.viettel.shopme.brand;

import com.viettel.shopme.admin.brand.BrandRepository;
import com.viettel.shopme.common.entity.Brand;
import com.viettel.shopme.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository repo;

    @Test
    public void testCreateBrand1() {
        Category laptops = new Category(6);
        Brand acer = new Brand("Acer");
        acer.getCategories().add(laptops);

        Brand savedBrand = repo.save(acer);

        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateBrand2() {
        Category category = new Category(4);
        Category category2 = new Category(7);
        Brand brand = new Brand("Apple");
        brand.getCategories().add(category);
        brand.getCategories().add(category2);

        Brand savedBrand = repo.save(brand);

        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getId()).isGreaterThan(0);
        assertThat(savedBrand.getCategories().size()).isGreaterThan(1);
    }
    @Test
    public void testCreateBrand3() {
        Category category = new Category(29);
        Category category2 = new Category(24);
        Brand brand = new Brand("Samsung");
        brand.getCategories().add(category);
        brand.getCategories().add(category2);

        Brand savedBrand = repo.save(brand);

        assertThat(savedBrand).isNotNull();
        assertThat(savedBrand.getId()).isGreaterThan(0);
        assertThat(savedBrand.getCategories().size()).isGreaterThan(1);
    }

    @Test
    public void testFindAllBrand() {
        List<Brand> brands = (List<Brand>) repo.findAll();
        for (Brand brand : brands) {
            System.out.println("id: "+ brand.getId());
            System.out.println("name: " + brand.getName());
            System.out.println(brand.getCategories().stream().map(category -> "category name: " + category.getName()).collect(Collectors.toList()));
        }
    }

    @Test
    public void testGetBrandById() {
        int id = 1;
        Brand brand = repo.findById(id).get();
        System.out.println(brand.toString());
        assertThat(brand).isNotNull();
    }

    @Test
    public void testUpdateBrand() {
        int id = 3;
        Brand brand = repo.findById(id).get();
        brand.setName("Samsung Electronics");
        repo.save(brand);
        System.out.println(brand.toString());
    }

    @Test
    public void testDeleteBrand() {
        int id = 2;
        repo.deleteById(id);
    }

}
