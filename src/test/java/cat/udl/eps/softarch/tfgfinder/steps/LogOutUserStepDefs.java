package cat.udl.eps.softarch.tfgfinder.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

public class LogOutUserStepDefs {

    private final StepDefs stepDefs;

    public LogOutUserStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @When("^I log out$")
    public void iLogOut() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/logout")
                        .session(stepDefs.session)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @Then("^The response code is (\\d+)$")
    public void theResponseCodeIs(int expectedStatus) throws Exception {
        stepDefs.result.andExpect(status().is(expectedStatus));
    }

    @Then("^I am not authenticated after logout$")
    public void iAmNotAuthenticatedAfterLogout() throws Exception {
        stepDefs.mockMvc.perform(
                get("/identity")
                        .session(stepDefs.session)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isUnauthorized());
    }
}
