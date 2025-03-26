package cat.udl.eps.softarch.tfgfinder.steps;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

public class EditProfileUserStepDefs {

    private final StepDefs stepDefs;

    public EditProfileUserStepDefs(StepDefs stepDefs) {
        this.stepDefs = stepDefs;
    }

    @When("^I update my profile with email \"([^\"]*)\"$")
    public void iUpdateMyProfileWithEmail(String newEmail) throws Exception {
        String payload = String.format("{\"email\": \"%s\"}", newEmail);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/users/user") // Assuming path is /users/{username}
                        .with(AuthenticationStepDefs.authenticate())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());
    }

    @And("^The profile contains the email \"([^\"]*)\"$")
    public void theProfileContainsEmail(String email) throws Exception {
        stepDefs.mockMvc.perform(
                get("/users/user")
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(jsonPath("$.email").value(email));
    }
}
