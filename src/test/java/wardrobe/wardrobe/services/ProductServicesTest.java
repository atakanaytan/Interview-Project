package wardrobe.wardrobe.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.exceptions.CategoryNotFoundException;
import wardrobe.wardrobe.repositories.CategoryRepository;
import wardrobe.wardrobe.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


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
        products.add(product);

        category.setProducts(products);

        when(productRepository.save(product)).thenReturn(product);
        assertEquals(productServices.addProduct(category.getId(), product), product);
    }


    @Test
    public void testFindProductsByCategoryId() {

        Assertions.assertThrows(CategoryNotFoundException.class, () -> {

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        Product product = new Product();

        product.setId(2L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        lenient().when(productRepository.findProductsByCategory_Id(category.getId())).thenReturn(category.getProducts());
        assertEquals( productServices.findProductsByCategoryId(category.getId()).size(), 1);

        });
    }


    @Test
    public void testGetProductById() {

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

        when(productRepository.getById(product.getId())).thenReturn(product);
        assertEquals(productServices.getProductById(product.getId()), product);
    }


    @Test
    void testUpdateProjectById() {

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        Product product = new Product();

        product.setId(2L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        when(productRepository.getById(product.getId())).thenReturn(product);

        product.setProductName("Test-Product-2");

        when(productRepository.save(product)).thenReturn(product);
        assertEquals(productServices.updateProjectById(product, product.getId()), product);
    }

    @Test
    public void testDeleteProduct() {

        Category category = new Category();
        category.setName("Test");
        category.setId(1L);

        Product product = new Product();

        product.setId(2L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);

        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        category.setProducts(products);

        when(productRepository.getById(product.getId())).thenReturn(product);
        productServices.deleteProduct(product.getId());
        verify(productRepository,times(1)).delete(product);
    }
}
