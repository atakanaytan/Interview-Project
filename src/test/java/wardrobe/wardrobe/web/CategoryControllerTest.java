package wardrobe.wardrobe.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.services.CategoryServices;
import wardrobe.wardrobe.services.ProductServices;
import wardrobe.wardrobe.services.ValidationErrorServices;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CategoryServices categoryServices;

    @MockBean
    private ProductServices productServices;

    @MockBean
    private ValidationErrorServices validationErrorServices;

    @Test
    public void testCreateNewCategory() throws Exception{

        Category category = new Category();
        category.setId(1l);
        category.setName("Test");

        String inputInJson = this.mapToJson(category);

        String URI = "/api/category";

        // given
        when(categoryServices.saveCategory(any(Category.class))).thenReturn(category);

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        // then
        String outputInJson = response.getContentAsString();
        assertEquals(outputInJson, inputInJson);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void testGetProducts() throws Exception {

        Category mockCategory = new Category();
        mockCategory.setName("Test-Product");
        mockCategory.setId(1L);

        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setProductName("Test-Product");
        mockProduct.setProjectDescription("Test");
        mockProduct.setProductPrice(1.0);
        mockProduct.setQuantity(15);
        mockProduct.setCategory(mockCategory);

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(mockProduct);

        mockCategory.setProducts(mockProducts);

        // given
        when(productServices.findProductsByCategoryId(anyLong())).thenReturn(mockProducts);

        // when
        String URI ="/api/category/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // then
        String expectedJson = this.mapToJson(mockProducts);
        String outputJson = result.getResponse().getContentAsString();
        assertEquals(outputJson, expectedJson);

    }

    @Test
    public void testGetAllProjects() throws Exception{

        Category mockCategory1= new Category();
        mockCategory1.setId(1L);
        mockCategory1.setName("Test1");

        Category mockCategory2= new Category();
        mockCategory2.setId(1L);
        mockCategory2.setName("Test2");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(mockCategory1);
        categoryList.add(mockCategory2);

        // given
        when(categoryServices.findAllCategories()).thenReturn(categoryList);

        // when
        String URI ="/api/category/all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // then
        String expectedJson = this.mapToJson(categoryList);
        String outputInJson = result.getResponse().getContentAsString();

        assertEquals(outputInJson, expectedJson);
    }

    @Test
    public void testDeleteCategory() throws Exception{

        // given
        doNothing().when(categoryServices).deleteCategoryById(1L);

        // when
        mockMvc.perform(delete(  "/api/category/{id}", 1L))
                .andExpect(status().isOk());

        // then
        verify(categoryServices).deleteCategoryById(1L);
    }


    /**
     * Maps an Object into a JSON String.
     */
    private String mapToJson(Object object) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}