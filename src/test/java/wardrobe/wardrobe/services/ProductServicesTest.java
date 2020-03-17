package wardrobe.wardrobe.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.repositories.CategoryRepository;
import wardrobe.wardrobe.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ProductServicesTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServices productServices;


    @Test
    public void testAddProduct() {

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
        category.setProducts(products);

        when(productRepository.save(product)).thenReturn(product);
        assertEquals(productServices.addProduct(category.getId(), product), product);
    }

    @Test
    public void testFindProductsByCategoryId() {

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
        category.setProducts(products);

       // Category result = categoryRepository.getById(1L);

        when(productRepository.findProductsByCategory_Id(category.getId())).thenReturn(products);
        assertEquals(productServices.findProductsByCategoryId(category.getId()), products);
    }

    @Test
    void testGetProductById() {
    }

    @Test
    void testUpdateProjectById() {
    }

    @Test
    void testDeleteProduct() {
    }
}