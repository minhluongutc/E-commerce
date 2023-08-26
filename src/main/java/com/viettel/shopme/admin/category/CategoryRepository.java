package com.viettel.shopme.admin.category;

import com.viettel.shopme.common.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
    @Query("select c from Category c where c.parent.id is null ")
    public List<Category> findRootCategories(Sort sort);

    @Query("select c from Category c where c.parent.id is null ")
    public Page<Category> findRootCategories(Pageable pageable);

    @Query("select c from Category c where c.name like %?1%")
    public Page<Category> search(String keyword ,Pageable pageable);

    public Category findByName(String name);

    public Category findByAlias(String alias);

    @Query("UPDATE Category c SET c.enabled = ?2 WHERE c.id = ?1")
    @Modifying
    public void updateEnabledStatus(Integer id, boolean enabled);

    public Long countById(Integer id);

    @Query("select c from Category c order by c.name asc")
    public List<Category> findAll();
}
