package com.fun.abm.FileResourceHandler.resolver;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.AbstractResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ImagePathIgnoreCaseResolver extends AbstractResourceResolver {

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request,
                                               String requestPath, List<? extends Resource> locations,
                                               ResourceResolverChain chain) {
        return getImgResource(requestPath, locations);
    }

    private Resource getImgResource(String requestPath, List<? extends Resource> locations) {

        String baseName = FilenameUtils.getBaseName(requestPath);
        String extension = FilenameUtils.getExtension(requestPath);

        Collection<String> validFilenames = new ArrayList<>();

        if (extension.isEmpty()) {
            validFilenames.add(baseName);
        } else {
            validFilenames.add(baseName + "." + extension.toLowerCase());
            validFilenames.add(baseName + "." + extension.toUpperCase());
        }

        return validFilenames.stream()
                .flatMap(fileName -> locations.stream().map(location -> {
                            try {
                                return location.createRelative(fileName);
                            } catch (IOException e) {
                                return null;
                            }
                        })
                ).filter(res -> res != null && res.exists())
                .findFirst().orElse(null);
    }

    @Override
    protected String resolveUrlPathInternal(String resourceUrlPath, List<? extends
            Resource> locations, ResourceResolverChain chain) {
        return null;
    }
}
