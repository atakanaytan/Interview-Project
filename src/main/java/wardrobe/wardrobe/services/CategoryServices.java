package wardrobe.wardrobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.exceptions.CategoryNameException;
import wardrobe.wardrobe.repositories.CategoryRepository;

import java.util.List;


@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;


    //Add category
    public Category saveCategory(Category category) {

        try {

            return categoryRepository.save(category);
        } catch (Exception e) {

            throw new CategoryNameException("Category Name '"+category.getName()+"' already exist ");
        }
    }


    //Fetch category based on id
    public Category findCategoryById (long categoryId) {

        Category category = categoryRepository.getById((categoryId));

        if ((category == null)) {

            throw new CategoryNameException("Category Id '"+categoryId+"' could not find due to it does not exist ");
        }

        return category;
    }


    //Fetch list of categories
    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }


    //Delete a category based on given id
    public void deleteCategoryById (Long categoryId) {

        Category category = categoryRepository.getById(categoryId);

        if (category == null) {

            throw new CategoryNameException("Category Id '"+categoryId+"' does not exist. Could not deleted.");
        }

        categoryRepository.delete(category);
    }

}
