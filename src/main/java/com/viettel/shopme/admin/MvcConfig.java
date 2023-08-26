package com.viettel.shopme.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "user-photos";
        Path userPhotosDir = Paths.get(dirName);

        String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/ShopmeAdmin/" + dirName + "/**")
                .addResourceLocations("file:/" + userPhotosPath + "/");

//        config categories src
        String categoryImageDirName = "categories-images";
        Path categoryImagesDir = Paths.get(categoryImageDirName);

        String categoryImagePath = categoryImagesDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/ShopmeAdmin/" + categoryImageDirName + "/**")
                .addResourceLocations("file:/" + categoryImagePath + "/");

//        config brands src
        String brandLogoDirName = "brands-logos";
        Path brandLogoDir = Paths.get(brandLogoDirName);

        String brandLogoPath = brandLogoDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/ShopmeAdmin/" + brandLogoDirName + "/**")
                .addResourceLocations("file:/" + brandLogoPath + "/");
    }
}
