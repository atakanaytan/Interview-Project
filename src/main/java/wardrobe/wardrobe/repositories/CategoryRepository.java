package wardrobe.wardrobe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wardrobe.wardrobe.domain.Category;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category getById(Long categoryId);

    @Override
    Iterable<Category> findAll();

}
