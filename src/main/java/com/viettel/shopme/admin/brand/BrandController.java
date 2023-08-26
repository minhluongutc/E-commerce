package com.viettel.shopme.admin.brand;

import com.viettel.shopme.admin.category.CategoryService;
import com.viettel.shopme.common.entity.Brand;
import com.viettel.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ShopmeAdmin")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/brands")
    public String getAllBrand(Model model) {
        List<Brand> brands = brandService.getAllBrand();

        model.addAttribute("brands", brands);
        return "brands/brands";
    }

    @GetMapping("/brands/new")
    public String newBrand(Model model) {
        List<Category> categories = categoryService.listCategoriesUsedInForm();

        model.addAttribute("brand", new Brand());
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Create New Brand");

        return "brands/brand_form";
    }
}
