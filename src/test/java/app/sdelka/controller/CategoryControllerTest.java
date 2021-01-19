package app.sdelka.controller;

import app.sdelka.controller.dto.CategoryDto;
import app.sdelka.service.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryServiceMapping;


    @Test
    void allCategoriesTest() {
        final CategoryDto rootCategoryDto = new CategoryDto();
        rootCategoryDto.setName("Любая категория");


        final CategoryDto parentCategoryDto = new CategoryDto();
        parentCategoryDto.setName("Транспорт");

        final List<CategoryDto> childCategories = new ArrayList<>();
        childCategories.add(parentCategoryDto);


        rootCategoryDto.setChildCategories(childCategories);


        Mockito.when(categoryServiceMapping.findAll()).thenReturn(rootCategoryDto);

        String url = "/api/v1/category/all";

        try {
            mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}