package cat.udl.eps.softarch.tfgfinder.steps;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.ResultMatcher;

public class InviteStepDefs {

    @Autowired
    private StepDefs stepDefs;

    private String loggedInUser;
    private String inviteeUser;
    private String proposalName;

    @Given("I am logged in as a student with name {string}")
    public void iAmLoggedInAsAStudent(String name) {

        loggedInUser = name;

        AuthenticationStepDefs.currentUsername = name;
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

    @And("There is a registered student with name {string}")
    public void thereIsARegisteredStudentWithUsername(String username) throws Exception {
        RegisterStepDefs registerStepDefs = new RegisterStepDefs();
        registerStepDefs.thereIsARegisteredUserWithUsernameAndPasswordAndEmail(username, "student2@example.com", "student2password");
    }

    @When("I send an invite to {string} for the proposal {string}")
    public void iSendAnInviteToForTheProposal(String invitee, String proposal) throws Exception {
        inviteeUser = invitee;
        proposalName = proposal;

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/invitations")
                        .param("student", loggedInUser)
                        .param("invitee", inviteeUser)
                        .param("proposal", proposalName)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @Then("The response code is {int}")
    public void theResponseCodeIs(int expectedCode) throws Exception {
        stepDefs.result.andExpect(status().is(expectedCode));
    }

    @And("The user {string} has a pending invitation for {string}")
    public void theUserHasAPendingInvitationFor(String username, String proposalName) throws Exception {
        stepDefs.result.andExpect((ResultMatcher) jsonPath("$..user", is(username)))
                .andExpect((ResultMatcher) jsonPath("$..proposal", is(proposalName)))
                .andExpect((ResultMatcher) jsonPath("$..status", is("pending")));
    }

    @Then("The error message is {string}")
    public void theErrorMessageIs(String expectedErrorMessage) throws Exception {
        stepDefs.result.andExpect((ResultMatcher) jsonPath("$..message", is(expectedErrorMessage)));
    }

}
