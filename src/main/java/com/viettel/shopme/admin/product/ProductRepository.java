package com.viettel.shopme.admin.product;

import com.viettel.shopme.common.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
}
