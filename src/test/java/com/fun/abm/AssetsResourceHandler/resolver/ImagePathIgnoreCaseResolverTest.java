package com.fun.abm.AssetsResourceHandler.resolver;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ImagePathIgnoreCaseResolverTest {

    private ImagePathIgnoreCaseResolver imagePathIgnoreCaseResolver;
    private ClassPathResource classPathResource;
    private List<ClassPathResource> classPathResources;

    @Before
    public void setUp() throws Exception {
        classPathResource = new ClassPathResource("images/");
        imagePathIgnoreCaseResolver = new ImagePathIgnoreCaseResolver();
        classPathResources = Collections.singletonList(classPathResource);
    }

    @Test
    public void shouldSearchForImagesWithLowerCase() throws Exception {
        checkForImageFileResource("testImage.png", "testImage.png");
    }

    @Test
    public void shouldSearchForImagesWithUpperCase() throws Exception {
        checkForImageFileResource("testImage.PNG", "testImage.png");
    }

    @Test
    public void shouldSearchForImagesWithoutFileExtension() throws Exception {
        checkForImageFileResource("testImageWithNoExtension", "testImageWithNoExtension");
    }

    private void checkForImageFileResource(String requestPath,
                                           String expectedRequestPath) {
        Resource imageResource = imagePathIgnoreCaseResolver.resolveResourceInternal(null, requestPath, classPathResources, null);

        assertThat(imageResource).isNotNull();
        assertThat(imageResource.getFilename()).isEqualTo(expectedRequestPath);
    }
}
