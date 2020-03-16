package wardrobe.wardrobe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.exceptions.CategoryNameException;
import wardrobe.wardrobe.exceptions.ProductNotFoundException;
import wardrobe.wardrobe.services.ProductServices;
import wardrobe.wardrobe.services.ValidationErrorServices;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    private ProductServices productServices;

    @Autowired
    private ValidationErrorServices validationErrorServices;


    /**
     * Create a product based on the category_id from giving as a url parameter.
     *
     * @param category_id - to indicate which category the product belongs to.
     *
     * @return new product
     *
     * @throws CategoryNameException exception
     *          if category_id does not exist.
     *
     *          errorMap-> if mandatory fields are null it returns validation errors
     */
    @RequestMapping(value = "/{category_id}", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product,
                                              BindingResult result, @PathVariable long category_id) {

      ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);

      if (errorMap != null) {
          return errorMap;
      }

        Product newProduct = productServices.addProduct(category_id, product);

        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }


    /**
     * Fetch a product with the related given parameter
     *
     * @param productId - Id of expected product
     *
     * @return Expected product
     *
     * @throws ProductNotFoundException
     *          if productId does not exist in the database
     *
     */
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoryById(@PathVariable long productId) {

        Product product = productServices.getProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }


    /**
     * Update an existing product
     *
     * @param productId - The id of the product to be updated
     *
     * @return Updated product as an object.
     *
     * @throws ProductNotFoundException
     *          if productId does not exist in the database
     *
     *          errorMap-> if mandatory fields are null it returns validation errors
     */
    @RequestMapping(value = "/{productId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updatedProduct(@Valid @RequestBody Product product, BindingResult result,
                                            @PathVariable long productId) {

        ResponseEntity<?> errorMap = validationErrorServices.ValidationErrorServices(result);

        if (errorMap != null) {
            return errorMap;
        }

        Product updatedProduct = productServices.updateProjectById(product, productId);

        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }


    /**
     * Delete a product
     *
     * @param productId - The id of the product to be deleted
     *
     * @return If delete operation is successful it returns message with deleted products id.
     *
     * @throws ProductNotFoundException
     *          if productId does not exist in the database
     *
     *          errorMap-> if mandatory fields are null it returns validation errors
     */
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable long productId) {
        productServices.deleteProduct(productId);

        return new ResponseEntity<String>("Product with id: '"+productId+"' was deleted succesfully", HttpStatus.OK);
    }

}

