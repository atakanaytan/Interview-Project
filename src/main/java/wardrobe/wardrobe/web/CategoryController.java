package wardrobe.wardrobe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.exceptions.CategoryNameException;
import wardrobe.wardrobe.services.CategoryServices;
import wardrobe.wardrobe.services.ProductServices;
import wardrobe.wardrobe.services.ValidationErrorServices;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private ValidationErrorServices validationErrorServices;


    /**
     * Create a category
     *
     * @return New category object
     *
     * @throws CategoryNameException exception
     *          if categoryName is exist, it returns given categoryName is already exist message.
     *
     *          errorMap-> if mandatory fields are null it returns validation errors
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createNewCategory (@Valid @RequestBody Category category, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);

        if (errorMap != null) {
            return errorMap;
        }

        Category category1 = categoryServices.saveCategory(category);

        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }


    /**
     * Fetch list of products with given categoryId
     *
     * @return List of products
     *
     * @throws CategoryNameException exception
     *          if categoryId is not exist, it returns categoryId does not exist
     *
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public Iterable<Product> getProducts(@PathVariable Long categoryId) {

        return productServices.findProductsByCategoryId(categoryId);
    }

    /**
     * Fetch all categories
     *
     * @return List of categories (id, categoryName, create, update details)
     *
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Category> getAllProjects() {

        return categoryServices.findAllCategories();
    }


    /**
     * Delete Product or products based on given parameter categoryId
     *
     * @return Success message i.e Category with ID: '3' was deleted
     *
     * @throws CategoryNameException exception
     *          if categoryId is not exist, it returns categoryId does not exist
     *
     */
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable long categoryId) {

        categoryServices.deleteCategoryById(categoryId);

        return new ResponseEntity<String>("Category with ID: '"+categoryId+"' was deleted", HttpStatus.OK);
    }


}
