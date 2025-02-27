package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ChatRepository extends CrudRepository<Chat, Long>, PagingAndSortingRepository<Chat, Long> {

    List<Chat> findByProposalId(Long proposal_id);


}
