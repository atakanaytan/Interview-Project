package wardrobe.wardrobe.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.exceptions.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProductServicesIntegrationTest {

    @Autowired
    private ProductServices productServices;

    @Autowired
    private CategoryServices categoryServices;


    @Transactional
    @Test
    public void testAddProduct() {

        Category category = new Category();
        category.setName("Test-Product");
        category.setId(1L);

        Category testCategory = categoryServices.saveCategory(category);

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

        Product testProduct = productServices.addProduct(testCategory.getId(), product);

        assertTrue(testProduct.getCategory() == testCategory);
    }


    @Test
    public void testFindProductsByCategoryId() {

        Category category = new Category();
        category.setName("Test-Product");
        category.setId(1L);

        Category testCategory = categoryServices.saveCategory(category);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Test-Product-2");
        product2.setProjectDescription("Test-2");
        product2.setProductPrice(2.0);
        product2.setQuantity(16);
        product2.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);

        testCategory.setProducts(products);

        //Need to check database created these instances to fetch products
        Product testProduct1 = productServices.addProduct(testCategory.getId(), product);
        Product testProduct2 = productServices.addProduct(testCategory.getId(), product2);

        List<Product> fetchProductsFromCategory =  productServices.findProductsByCategoryId(category.getId());

        assertEquals(fetchProductsFromCategory.size(), 2);
    }


    @Test
    public void getProductById() {

        Category category = new Category();
        category.setName("Test-Product");
        category.setId(1L);

        Category testCategory = categoryServices.saveCategory(category);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        testCategory.setProducts(products);

        Product testProduct = productServices.addProduct(testCategory.getId(), product);
        Product result = productServices.getProductById(testProduct.getId());

        assertEquals(product.getProductName(), result.getProductName());
    }


    @Test
    public void updateProjectById() {

        Category category = new Category();
        category.setName("Test-Product");
        category.setId(1L);

        Category testCategory = categoryServices.saveCategory(category);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        testCategory.setProducts(products);

        Product testProduct = productServices.addProduct(testCategory.getId(), product);
        Product updateProduct = productServices.getProductById(testProduct.getId());

        updateProduct.setProductName("Updated-Product");

        Product updated = productServices.addProduct(product.getId(), updateProduct);

        assertNotEquals(updated.getProductName(), product.getProductName() );
    }

    @Test
    public void deleteProduct() {

        Assertions.assertThrows(ProductNotFoundException.class, () -> {

        Category category = new Category();
        category.setName("Test-Product");
        category.setId(1L);

        Category testCategory = categoryServices.saveCategory(category);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test-Product");
        product.setProjectDescription("Test");
        product.setProductPrice(1.0);
        product.setQuantity(15);
        product.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product);

        testCategory.setProducts(products);

        Product testProduct = productServices.addProduct(testCategory.getId(), product);

        productServices.deleteProduct(testProduct.getId());

        Product result = productServices.getProductById(testProduct.getId());

        assertNull(result);

        });
    }
}
