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
        exportDirectory("user-photos", registry);
        exportDirectory("categories-images", registry);
        exportDirectory("brands-logos", registry);
    }

    private void exportDirectory(String pathPattern, ResourceHandlerRegistry registry) {
        Path path = Paths.get(pathPattern);
        String absolutePath = path.toFile().getAbsolutePath();

        String logicalPath = pathPattern + "/**";

        registry.addResourceHandler("/ShopmeAdmin/" + logicalPath)
                .addResourceLocations("file:/" + absolutePath + "/");
    }
}
