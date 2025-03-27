package cat.udl.eps.softarch.tfgfinder.steps;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cat.udl.eps.softarch.tfgfinder.config.WebSecurityConfig;
import cat.udl.eps.softarch.tfgfinder.domain.Student;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.test.web.servlet.ResultMatcher;

public class InviteStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private UserRepository studentRepository;

    @Autowired
    private UserRepository professorRepository;

    @And("^\"([^\"]*)\" is a student")
    public void itsStudent(String username) {
        Assert.assertTrue("User \"" + username + "\" exists",
                studentRepository.existsById(username));
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
