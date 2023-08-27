package com.viettel.shopme.admin.brand;

import com.viettel.shopme.common.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {
    public Long countById(Integer id);
    public Brand findByName(String name);
    @Query("select b from Brand b where b.name like %?1%")
    public Page<Brand> findAll(String keyword, Pageable pageable);
}
