package wardrobe.wardrobe.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.repositories.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CategoryServicesTest {


    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServices categoryServices;


    @Test
    public void testSaveCategory() {

        Category category = new Category();
        category.setName("Test Category");

        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(categoryServices.saveCategory(category), category);
    }


    @Test
    public void testFindCategoryById() {

        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(categoryRepository.getById(1L)).thenReturn(category);
        assertEquals(categoryServices.findCategoryById(1l), category);
    }


    @Test
    void findAllCategories() {

        Category category1 = new Category();
        category1.setName("Test Category");

        Category category2 = new Category();
        category2.setName("Test Category");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);

        when(categoryRepository.findAll()).thenReturn(categoryList);
        assertEquals(categoryServices.findAllCategories(), categoryList);
    }


    @Test
    public void deleteCategoryById() {

        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");


        when(categoryRepository.getById(1L)).thenReturn(category);

        categoryServices.deleteCategoryById(category.getId());

        verify(categoryRepository, times(1)).delete(category);
    }

}
