package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Admin;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AdminRepository extends CrudRepository<Admin, String>, PagingAndSortingRepository<Admin, String> {
    List<User> findByIdContaining(@Param("text") String text);
}