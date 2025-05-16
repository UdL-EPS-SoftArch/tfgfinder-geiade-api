package cat.udl.eps.softarch.tfgfinder.controller;

import cat.udl.eps.softarch.tfgfinder.domain.*;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserRepository userRepository;

    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register-student")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        return registerUser(student);
    }

    @PostMapping("/register-professor")
    public ResponseEntity<?> registerProfessor(@RequestBody Professor professor) {
        return registerUser(professor);
    }

    @PostMapping("/register-organisation")
    public ResponseEntity<?> registerOrganisation(@RequestBody External external) {
        return registerUser(external);
    }

    private ResponseEntity<?> registerUser(User user) {
        if (userRepository.existsById(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Unique index or primary key violation");
        }
        user.encodePassword();
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
