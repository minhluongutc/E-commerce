package com.viettel.shopme.admin.brand;

import com.viettel.shopme.admin.FileUploadUtil;
import com.viettel.shopme.admin.category.CategoryService;
import com.viettel.shopme.common.entity.Brand;
import com.viettel.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/brands/edit/{id}")
    public String editBrand(Model model,
                            @PathVariable("id") Integer id,
                            RedirectAttributes ra) {
        try {
            List<Category> categories = categoryService.listCategoriesUsedInForm();
            Brand brand = brandService.get(id);
            model.addAttribute("brand", brand);
            model.addAttribute("categories", categories);
            model.addAttribute("pageTitle", "Edit Brand");

            return "brands/brand_form";
        } catch (BrandNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/ShopmeAdmin/brands";
        }
    }

    @PostMapping("/brands/save")
    public String save(Brand brand,
                       RedirectAttributes ra,
                       @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            brand.setLogo(fileName);

            Brand savedBrand = brandService.save(brand);
            String uploadDir = "brands-logos/" + savedBrand.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            brandService.save(brand);
        }

        ra.addFlashAttribute("message", "The brand has been saved successfully");
        return "redirect:/ShopmeAdmin/brands";
    }

    @GetMapping("/brands/delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes ra) throws BrandNotFoundException {
        try {
            brandService.delete(id);

            String  brandDir = "./brands-logos/" + id;
            FileUploadUtil.removeDir(brandDir);

            ra.addFlashAttribute("message", "Delete successfully brand with ID: "+id);
        } catch (BrandNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/ShopmeAdmin/brands";
    }
}
