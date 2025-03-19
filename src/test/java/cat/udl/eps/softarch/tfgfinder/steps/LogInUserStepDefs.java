package cat.udl.eps.softarch.tfgfinder.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class LogInUserStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private UserRepository userRepository;

    @Given("^There is a registered user with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void thereIsARegisteredUserWithUsernameAndPassword(String username, String password) {
        if (!userRepository.existsById(username)) {
            User user = new User();
            user.setId(username);
            user.setEmail(username + "@sample.app");
            user.setPassword(password);
            user.encodePassword();
            userRepository.save(user);
        }
    }

    @When("^I login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLoginWithUsernameAndPassword(String username, String password) throws Exception {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;

        stepDefs.result = stepDefs.mockMvc.perform(
                get("/identity")
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Then("^I am successfully authenticated$")
    public void iAmSuccessfullyAuthenticated() throws Exception {
        stepDefs.result.andExpect(status().isOk());
    }

    @Then("^I am not authenticated$")
    public void iAmNotAuthenticated() throws Exception {
        stepDefs.result.andExpect(status().isUnauthorized());
    }

    @And("^I remain logged in as \"([^\"]*)\"$")
    public void iRemainLoggedInAs(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/identity")
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }
}
