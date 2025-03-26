package cat.udl.eps.softarch.tfgfinder.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

public class ViewProfileUserStepDefs {

    private final StepDefs stepDefs;

    public ViewProfileUserStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @When("^I request the profile of user \"([^\"]*)\"$")
    public void iRequestTheProfileOfUser(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/users/" + username)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
                        .session(stepDefs.session)
        ).andDo(print());
    }

    @And("^The profile contains the username \"([^\"]*)\"$")
    public void theProfileContainsUsername(String expectedUsername) throws Exception {
        stepDefs.result.andExpect(jsonPath("$.username", is(expectedUsername)));
    }
}
