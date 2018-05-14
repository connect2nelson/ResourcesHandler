package com.fun.abm.AssetsResourceHandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "img.path=file:src/test/resources/images/",
        "css.path=file:src/test/resources/css/",
        "static.assets.path=file:src/test/resources/assets/",
        "debug=false",
        "trace=false"
})
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldServeImagesFromImageFolder() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/images/testImage.png"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldServeImagesCaseInsensitivelyFromImageFolder() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/images/testImageCase.png"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFoundIfImageDoesNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/images/invalidImage.png"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldServeCssFromCssFolder() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/css/testCss.css"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFoundIfCssDoesNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/css/invalidCss.css"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldServeAssetsFromAssetsFolder() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/assets/asset.svg"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnStatusNotFoundIfAssetsDoesNotExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/assets/nonasset.svg"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldServeImagesFromImageFolderIgnoringCaseForImageExtension() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/images/testImage.PNG"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
