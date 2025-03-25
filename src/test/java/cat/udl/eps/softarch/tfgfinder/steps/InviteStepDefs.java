package cat.udl.eps.softarch.tfgfinder.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class InviteStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Given("I am logged in as a student with name {string}")
    public void iAmLoggedInAsAStudent(String username) {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = "password";
    }

    @And("There is a registered professor with name {string}")
    public void thereIsARegisteredProfessorWithUsername(String username) throws Exception {
        RegisterStepDefs registerStepDefs = new RegisterStepDefs();
        registerStepDefs.thereIsARegisteredUserWithUsernameAndPasswordAndEmail(username, "professor1@example.com", "professor1password");
    }

    @And("There is a registered external user with name {string}")
    public void thereIsARegisteredExternalUserWithUsername(String username) throws Exception {
        RegisterStepDefs registerStepDefs = new RegisterStepDefs();
        registerStepDefs.thereIsARegisteredUserWithUsernameAndPasswordAndEmail(username, "external1@example.com", "external1password");
    }

    @And("There is a registered student with username {string}")
    public void thereIsARegisteredStudentWithUsername(String username) throws Exception {
        RegisterStepDefs registerStepDefs = new RegisterStepDefs();
        registerStepDefs.thereIsARegisteredUserWithUsernameAndPasswordAndEmail(username, "student2@example.com", "student2password");
    }

}
