package wardrobe.wardrobe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wardrobe.wardrobe.domain.Product;

import java.util.List;



@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product getById(Long productId);

    Product findCategory_IdById (Long productId);

    List<Product> findProductsByCategory_Id(Long categoryId);

}
