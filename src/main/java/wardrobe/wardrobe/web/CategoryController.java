package wardrobe.wardrobe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.services.CategoryServices;
import wardrobe.wardrobe.services.ValidationErrorServices;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private ValidationErrorServices validationErrorServices;

    @PostMapping("")
    public ResponseEntity<?> createNewCategory (@Valid @RequestBody Category category, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);

        if (errorMap != null) {
            return errorMap;
        }

        Category category1 = categoryServices.saveCategory(category);

        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable long categoryId) {

        Category category = categoryServices.findCategoryById(categoryId);

        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Category> getAllProjects() {

        return categoryServices.findAllCategories();
     }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable long categoryId) {

        categoryServices.deleteCategoryById(categoryId);

        return new ResponseEntity<String>("Project with ID: '"+categoryId+"' was deleted", HttpStatus.OK);
    }
}
