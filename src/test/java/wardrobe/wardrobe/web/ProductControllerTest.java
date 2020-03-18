package wardrobe.wardrobe.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.services.ProductServices;
import wardrobe.wardrobe.services.ValidationErrorServices;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServices productServices;

    @MockBean
    private ValidationErrorServices validationErrorServices;


    @Test
    public void testCreateProduct() throws Exception{

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        String inputInJson = this.mapToJson(product);

        String URI = "/api/product/1";

        // given
        when(productServices.addProduct(anyLong(), any(Product.class))).thenReturn(product);

        // when
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
    public void testGetProductById() throws Exception{

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        String inputInJson = this.mapToJson(product);

        String URI = "/api/product/1";

        // given
        when(productServices.getProductById(anyLong())).thenReturn(product);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        // then
        String outputInJson = response.getContentAsString();
        assertEquals(outputInJson, inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void testUpdatedProduct() throws Exception{

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        String inputInJson = this.mapToJson(product);


        product.setProductName(eq("Test-Update"));

        // given
        when(productServices.updateProjectById(any(Product.class),1L)).thenReturn((any(Product.class)));

        // when
        mockMvc.perform(patch("/api/product/{id}", 1)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        verify(productServices, times(1)).updateProjectById(any(Product.class), anyLong());
    }


    @Test
    public void testDeleteProduct() throws Exception{

        // given
        doNothing().when(productServices).deleteProduct(1L);

        // when
        mockMvc.perform(delete(  "/api/product/{id}", 1L))
                .andExpect(status().isOk());

        // then
        verify(productServices).deleteProduct(1L);
    }


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}