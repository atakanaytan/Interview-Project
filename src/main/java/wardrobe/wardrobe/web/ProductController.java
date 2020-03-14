package wardrobe.wardrobe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.services.ProductServices;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    //add project

    @PostMapping("")
    public ResponseEntity<Product> createNewProject(@RequestBody Product product) {
        Product savedProduct = productServices.saveProject(product);
        return  new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

}
