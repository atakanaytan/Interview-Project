package wardrobe.wardrobe.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.exceptions.CategoryNameException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CategoryServicesIntegrationTest {

    @Autowired
    private CategoryServices categoryServices;


    @Test
    public void testSaveCategory() {

       Category category = new Category();
       category.setName("Test Category");

       Category result = categoryServices.saveCategory(category);
       assertTrue(result.getId() > 0L);

       //After the operation delete instance in the database
       categoryServices.deleteCategoryById(category.getId());
    }


   @Test
   public void testFindAllCategories() {

       Category category = new Category();
       category.setName("Test Category");

       Category result = categoryServices.saveCategory( category );

       List<Category> categories = categoryServices.findAllCategories();

       assertEquals(categories.size(), 1);
   }


    @Test
    public void testDeleteCategoryById() {

        Assertions.assertThrows(CategoryNameException.class, () -> {

            Category category = new Category();
            category.setName("Test Category");

            Category saveCategory = categoryServices.saveCategory( category );

            categoryServices.deleteCategoryById(saveCategory.getId());

            Category result = categoryServices.findCategoryById(saveCategory.getId());

            assertNull(result);
        });
    }

}
