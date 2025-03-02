package cat.udl.eps.softarch.tfgfinder.repository;

import java.util.Collection;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.domain.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProposalRepository extends CrudRepository<Proposal, Long>, PagingAndSortingRepository<Proposal, Long> {
  
    // Finds a list of proposals based on a specific "title".
    List<Proposal> findByTitle(@Param("title") String title);

    // Finds a list of proposals based on a specific "description".
    List<Proposal> findByDescription(@Param("description") String description);

    // Finds a list of proposals based on a specific "timing".
    List<Proposal> findByTiming(@Param("timing") String timing);

    // Finds a list of proposals based on a specific "speciality".
    List<Proposal> findBySpeciality(@Param("speciality") String speciality);

    // Finds a list of proposals based on a specific "kind".
    List<Proposal> findByKind(@Param("kind") String kind);

    // Finds a list of proposals based on a specific "keywords".
    List<Proposal> findByKeywords(@Param("keywords") Collection<String> keywords);

    // Finds a list of proposals based on a specific user object.
    List<Proposal> findByUser(@Param("user") User user);

    // Finds a list of proposals based on a specific student object.
    List<Proposal> findByStudent(@Param("student") Student student);
}