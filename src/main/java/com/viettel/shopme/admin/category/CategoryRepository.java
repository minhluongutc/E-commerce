package com.viettel.shopme.admin.category;

import com.viettel.shopme.common.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    @Query("select c from Category c where concat(c.id, ' ', c.name, ' ', c.alias) like %?1%")
    public Page<Category> findAll(String keyword, Pageable pageable);

    @Query("select c from Category c where c.parent.id is null ")
    public List<Category> findRootCategories();

    public Category findByName(String name);

    public Category findByAlias(String alias);
}
