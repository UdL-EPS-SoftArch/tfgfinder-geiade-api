package cat.udl.eps.softarch.tfgfinder.repository;


import cat.udl.eps.softarch.tfgfinder.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {

    Optional<Category> findByName(@Param("name") String name); //Find by the exact name
    List<Category> findByNameContainingIgnoreCase(@Param("name") String name); //Find by partial name matching to find it when not written correctly


}


