package com.viettel.shopme.admin.category;

import com.viettel.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    public static final int CATEGORY_PER_PAGE = 10;
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> findAll() {
        return (List<Category>) categoryRepo.findAll(Sort.by("name").ascending());
    }

    public Page<Category> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1, CATEGORY_PER_PAGE, sort);

        if(keyword != null) {
            return categoryRepo.findAll(keyword, pageable);
        }

        return categoryRepo.findAll(pageable);
    }
}
