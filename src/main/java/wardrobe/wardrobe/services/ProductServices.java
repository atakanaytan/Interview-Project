package wardrobe.wardrobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wardrobe.wardrobe.domain.Category;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.exceptions.CategoryNameException;
import wardrobe.wardrobe.exceptions.CategoryNotFoundException;
import wardrobe.wardrobe.exceptions.ProductNotFoundException;
import wardrobe.wardrobe.repositories.CategoryRepository;
import wardrobe.wardrobe.repositories.ProductRepository;


@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    //Add product
    public Product addProduct(long categoryId, Product product) {

        try {

            //Fetch the category to be contain the product
            Category category = categoryRepository.getById(categoryId);
            //Set the category as new products category_id on Database
            product.setCategory(category);

            return productRepository.save(product);

        } catch(Exception e) {

            throw new CategoryNotFoundException
                    ("Product could not added due to categoryId: '"+categoryId+"' does not exist");
        }
    }


    //Fetch list of products based on categoryId
    public Iterable<Product> findProductsByCategoryId (long categoryId) {

        Category category = categoryRepository.getById(categoryId);

        if (category == null) {

            throw new CategoryNotFoundException("Products related with id: '"+categoryId+"' does not exist");
        }

        return productRepository.findProductsByCategory_Id(categoryId);
    }


    //Fetch product based on id
    public Product getProductById (long productId) {

        Product product = productRepository.getById((productId));

        if (product == null) {

            throw new ProductNotFoundException("Product Id: '"+productId+"' does not exist ");
        }

        return product;
    }


    //Update product
    public Product updateProjectById(Product updatedProduct, long productId){

        //Parameter updatedProduct does not have categoryId
        //Fetch the product of expected to update

        Product product = productRepository.getById(productId);

        if (product == null) {

            throw new ProductNotFoundException("Product Id: '"+productId+"' does not exist ");
        }

        //Fetch the categoryId of product before update
        Category productsCategoryId = product.getCategory();

        //Set the categoryId to updated product
        updatedProduct.setCategory(productsCategoryId);

        product = updatedProduct;

        return productRepository.save(product);
    }


    //Delete Product
    public void  deleteProduct(long productId) {

        Product product = getProductById(productId);

        productRepository.delete(product);
    }
}
