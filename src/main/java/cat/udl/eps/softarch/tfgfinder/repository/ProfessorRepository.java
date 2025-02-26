package cat.udl.eps.softarch.tfgfinder.repository;


import cat.udl.eps.softarch.tfgfinder.domain.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProfessorRepository extends CrudRepository<Professor, String>, PagingAndSortingRepository<Professor, String> {
  List<Professor> findByDepartmentContaining(@Param("department") String department);
}