package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Agree;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface AgreeRepository extends CrudRepository<Agree, Long>, PagingAndSortingRepository<Agree, Long> {
    List<Agree> findByWhen(@Param("when") ZonedDateTime when);
    List<Agree> findByStatus(@Param("status") String status);
    List<Proposal> findByProposal(@Param("proposal") Proposal proposal);
    List<User> findByUser(@Param("user") User user);

}