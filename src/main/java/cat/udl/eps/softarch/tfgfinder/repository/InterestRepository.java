package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Interest;
import cat.udl.eps.softarch.tfgfinder.domain.User;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InterestRepository extends CrudRepository<Interest, Long>, PagingAndSortingRepository<Interest, Long> {
    
    // Finds a list of interests based on a specific "when" (a ZonedDateTime) value.
    List<Interest> findByWhen(@Param("when")ZonedDateTime when);

    // Finds a list of interests based on the status (Interest.Status enum).
    List<Interest> findByStatus(@Param("status") Interest.Status status);

    // Finds a list of interests based on a user object.
    List<Interest> findByUser(@Param("user") User user);
}
