package cat.udl.eps.softarch.tfgfinder.steps;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.ExternalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProfessorRepository;
import cat.udl.eps.softarch.tfgfinder.repository.StudentRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class InviteStepDefs {

    @Autowired
    private StepDefs stepDefs;

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
    }

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

/*
//if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!studentRepository.existsById("student1")) {
                User student1 = new User();
                student1.setEmail("student1@sample.app");
                student1.setId("student1");
                student1.setPassword(defaultPassword);
                student1.encodePassword();
                studentRepository.save(student1);
            }
        //}
        //if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!studentRepository.existsById("student2")) {
                User student2 = new User();
                student2.setEmail("student2@sample.app");
                student2.setId("student2");
                student2.setPassword(defaultPassword);
                student2.encodePassword();
                studentRepository.save(student2);
            }
        //}
        //if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!professorRepository.existsById("professor1")) {
                User professor1 = new User();
                professor1.setEmail("professor1@sample.app");
                professor1.setId("professor1");
                professor1.setPassword(defaultPassword);
                professor1.encodePassword();
                professorRepository.save(professor1);
            }
        //}
        //if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!externalRepository.existsById("external1")) {
                User external1 = new User();
                external1.setEmail("external1@sample.app");
                external1.setId("external1");
                external1.setPassword(defaultPassword);
                external1.encodePassword();
                externalRepository.save(external1);
            }
        //}
 */
