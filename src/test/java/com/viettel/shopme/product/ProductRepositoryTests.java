package com.viettel.shopme.product;

import com.viettel.shopme.admin.product.ProductRepository;
import com.viettel.shopme.common.entity.Brand;
import com.viettel.shopme.common.entity.Category;
import com.viettel.shopme.common.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateProduct() {
        Brand brand = entityManager.find(Brand.class, 15);
        Category category = entityManager.find(Category.class, 6);

        Product product = new Product();
        product.setName("Dell Inspiron 3000");
        product.setAlias("Dell_inspiron_3000");
        product.setShortDescription("Short description for Dell inspiron 3000");
        product.setFullDescription("Full description for Dell inspiron 3000");

        product.setBrand(brand);
        product.setCategory(category);

        product.setPrice(456);
        product.setCost(400);
        product.setEnabled(true);
        product.setInStock(true);

        product.setCreatedTime(new Date());
        product.setUpdatedTime(new Date());

        Product savedProduct = repo.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProduct() {
        Iterable<Product> iterableProducts = repo.findAll();
        iterableProducts.forEach(System.out::println);
    }

    @Test
    public void testGetProduct() {
        Integer id = 2;
        Product product = repo.findById(id).get();
        System.out.println(product);
        assertThat(product).isNotNull();
    }

    @Test
    public void testUpdateProduct() {
        Integer id = 1;
        Product product = repo.findById(id).get();
        product.setPrice(499);

        repo.save(product);

        Product updatedProduct = entityManager.find(Product.class, id);

        assertThat(updatedProduct.getPrice()).isEqualTo(499);
    }

    @Test
    public void testDeleteProduct() {
        Integer id = 2;
        repo.deleteById(id);

        Optional<Product> result = repo.findById(id);

        assertThat(!result.isPresent());
    }
}
