package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long>, PagingAndSortingRepository<Proposal, Long> {
    // Get all the proposals by category
    List<Proposal> findByCategory(@Param("Category") Category category);
}


