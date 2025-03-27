package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.ExternalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProfessorRepository;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class InviteStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private UserRepository userRepository; //cal?

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ExternalRepository externalRepository;

    @Given("There is a registered student with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredStudentWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!studentRepository.existsById(username)) {
            User student = new User();
            student.setEmail(email);
            student.setId(username);
            student.setPassword(password);
            student.encodePassword();
            studentRepository.save(student);
        }
    } // mirar si fem 3 separats, o fem 1 genèric d'user i li posem ifs a dins, per fer cadasun

    @Given("There is a registered professor with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredProfessorWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!studentRepository.existsById(username)) {
            User professor = new User();
            professor.setEmail(email);
            professor.setId(username);
            professor.setPassword(password);
            professor.encodePassword();
            professorRepository.save(professor);
        }
    }

    @Given("There is a registered external with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void thereIsARegisteredExternalWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        if (!studentRepository.existsById(username)) {
            User external = new User();
            external.setEmail(email);
            external.setId(username);
            external.setPassword(password);
            external.encodePassword();
            externalRepository.save(external);
        }
    }

    @And("^\"([^\"]*)\" is a student")
    public void itIsStudent(String username) {
        Assert.assertTrue("User \"" + username + "\" exists",
                studentRepository.existsById(username));
    }

    @And("^\"([^\"]*)\" is a professor")
    public void itIsProfessor(String username) {
        Assert.assertTrue("User \"" + username + "\" exists",
                professorRepository.existsById(username));
    }

    @And("^\"([^\"]*)\" is an external")
    public void itIsExternal(String username) {
        Assert.assertTrue("User \"" + username + "\" exists",
                externalRepository.existsById(username));
    }
}
