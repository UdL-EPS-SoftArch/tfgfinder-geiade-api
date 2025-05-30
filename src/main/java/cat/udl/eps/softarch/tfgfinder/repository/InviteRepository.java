package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Invite;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface InviteRepository extends CrudRepository<Invite, Long>, PagingAndSortingRepository<Invite, Long> {
    List<Invite> findByInviteDate(@Param("inviteDate") ZonedDateTime inviteDate);
    List<Invite> findByStatus(@Param("status") String status);
    List<Invite> findByWho(@Param("who") User who);
    List<Invite> findByWhat(@Param("what") Proposal what);
    Invite findByWhoAndWhat(@Param("who") User who, @Param("what") Proposal what);
    Boolean existsByWho(@Param("who") User who);
}
