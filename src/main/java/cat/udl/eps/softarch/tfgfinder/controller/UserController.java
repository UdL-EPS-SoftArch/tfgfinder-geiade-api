package cat.udl.eps.softarch.tfgfinder.controller;

import cat.udl.eps.softarch.tfgfinder.domain.Student;
import cat.udl.eps.softarch.tfgfinder.domain.Professor;
import cat.udl.eps.softarch.tfgfinder.domain.External;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProfessorRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ExternalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final ExternalRepository externalRepository;

    public UserController(StudentRepository studentRepository,
                          ProfessorRepository professorRepository,
                          ExternalRepository externalRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.externalRepository = externalRepository;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        var student = studentRepository.findById(username);
        if (student.isPresent()) {
            return ResponseEntity.ok((Object) student.get());
        }

        var professor = professorRepository.findById(username);
        if (professor.isPresent()) {
            return ResponseEntity.ok((Object) professor.get());
        }

        var external = externalRepository.findById(username);
        if (external.isPresent()) {
            return ResponseEntity.ok((Object) external.get());
        }

        return ResponseEntity.notFound().build();
    }

}
