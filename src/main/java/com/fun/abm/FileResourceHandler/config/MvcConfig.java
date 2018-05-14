package com.fun.abm.FileResourceHandler.config;

import com.fun.abm.FileResourceHandler.resolver.ImagePathIgnoreCaseResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Value("${img.path}")
    private String imgPath;
    @Value("${css.path}")
    private String cssPath;
    @Value("${static.assets.path}")
    private String assetsPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/api/images/**")
                .addResourceLocations(imgPath)
                .resourceChain(true)
                .addResolver(new ImagePathIgnoreCaseResolver());

        registry.addResourceHandler("/api/css/**")
                .addResourceLocations(cssPath);

        registry.addResourceHandler("/api/assets/**")
                .addResourceLocations(assetsPath);
    }
}