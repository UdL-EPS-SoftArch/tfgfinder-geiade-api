package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ProposalRepository extends CrudRepository<Proposal, Long>, PagingAndSortingRepository<Proposal, Long> {
    List<Proposal> findProposalByCategory(@Param("category") Category category);
    
    // Get all the proposals by category
    List<Proposal> findByCategory(@Param("Category") Category category);
    Optional<Proposal> findByTitle(String title);
}

