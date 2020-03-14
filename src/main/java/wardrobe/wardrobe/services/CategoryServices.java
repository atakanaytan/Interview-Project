package wardrobe.wardrobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.exceptions.CategoryNameException;
import wardrobe.wardrobe.repositories.CategoryRepository;


@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {

        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new CategoryNameException("Category Name '"+category.getName()+"' already exist ");
        }

    }

    public Category findCategoryById (long categoryId) {

        Category category = categoryRepository.getById((categoryId));

        if ((category == null)) {

            throw new CategoryNameException("Category Id'"+categoryId+"' does not exist ");
        }

        return category;
    }

    public Iterable<Category>findAllCategories() {

        return categoryRepository.findAll();
    }

    public void deleteCategoryById (long categoryId) {

        Category category = categoryRepository.getById(categoryId);

        if (category == null) {

            throw new CategoryNameException("Category Id '"+categoryId+"' does not exist. Could not deleted.");
        }

        categoryRepository.delete(category);
    }

}
