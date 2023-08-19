package com.viettel.shopme.admin.category;

import com.viettel.shopme.common.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    @Query("select c from Category c where concat(c.id, ' ', c.name, ' ', c.alias) like %?1%")
    public Page<Category> findAll(String keyword, Pageable pageable);
}
