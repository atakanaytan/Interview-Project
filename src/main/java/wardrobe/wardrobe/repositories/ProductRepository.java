package wardrobe.wardrobe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wardrobe.wardrobe.domain.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    Iterable<Product> findAllById(Iterable<Long> iterable);

}
