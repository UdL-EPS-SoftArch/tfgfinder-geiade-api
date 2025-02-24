package cat.udl.eps.softarch.tfgfinder.repository;


import cat.udl.eps.softarch.tfgfinder.domain.External;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ExternalRepository extends CrudRepository<External, String>, PagingAndSortingRepository<External, String> {
  List<External> findByOrganizationContaining(@Param("organization") String organization);
}