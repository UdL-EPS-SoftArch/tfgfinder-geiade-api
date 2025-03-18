package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Agree;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface AgreeRepository extends CrudRepository<Agree, Long>, PagingAndSortingRepository<Agree, Long> {
    List<Agree> findAgreeByAgreeDate(@Param("agreeDate") ZonedDateTime agreeDate);
    List<Agree> findAgreeByStatus(Agree.@NotNull Status status);
    List<Agree> findAgreesByProposal(@Param("proposal") Proposal proposal);
    List<Agree> findAgreeByUser(@Param("user") User user);

}
