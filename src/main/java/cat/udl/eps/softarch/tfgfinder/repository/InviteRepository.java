package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Invite;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@RepositoryRestResource
public interface InviteRepository extends CrudRepository<Invite, Long>, PagingAndSortingRepository<Invite, Long> {
    List<Invite> findByInviteDate(@Param("inviteDate") ZonedDateTime inviteDate);
    List<Invite> findByStatus(@Param("status") String status);
    List<Invite> findByWho(@Param("who") User who);
    List<Invite> findByWhat(@Param("what") Proposal what);
}
