package cat.udl.eps.softarch.tfgfinder.repository;

import cat.udl.eps.softarch.tfgfinder.domain.Message;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;
import java.util.List;

@RepositoryRestResource
public interface MessageRepository extends CrudRepository<Message, Long>, PagingAndSortingRepository<Message, Long> {

    List<Message> findByWhen(@Param("when")ZonedDateTime when);

    List<Message> findByFrom(@Param("user") User from);

}
