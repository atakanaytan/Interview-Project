package wardrobe.wardrobe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wardrobe.wardrobe.domain.Product;
import wardrobe.wardrobe.repositories.ProductRepository;


@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProject(Product product) {

        return productRepository.save(product);
    }

}
