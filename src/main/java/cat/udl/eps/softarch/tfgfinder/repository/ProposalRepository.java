package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long>, PagingAndSortingRepository<Proposal, Long> {
    List<Proposal> findByTitle(@Param("title") String title);
    List<Proposal> findByCategory(@Param("Category") Category category);
    Boolean existsByTitle(@Param("title") String title);
}


