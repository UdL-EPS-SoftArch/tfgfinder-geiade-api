package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.CategoryRepository;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreateProposalStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ResultActions result;

    private User user;
    private Category category;
    private Proposal proposal;


    @Given("a category {string} with description {string} exists")
    public void aCategoryExists(String categoryName, String categoryDescription) throws Exception {
        category = new Category();
        category.setName(categoryName);
        category.setDescription(categoryDescription);
        categoryRepository.save(category);
    }

    @When("the user creates a proposal with title {string}")
    public void theUserCreatesAProposalWithTitle(String title) throws Exception {
        proposal = new Proposal();
        proposal.setTitle(title);
    }

    @And("description {string}")
    public void description(String description) {
        proposal.setDescription(description);
    }

    @And("timing {string}")
    public void timing(String timing) {
        proposal.setTiming(timing);
    }

    @And("speciality {string}")
    public void speciality(String speciality) {
        proposal.setSpeciality(speciality);
    }

    @And("kind {string}")
    public void kind(String kind) {
        proposal.setKind(kind);
    }

    @And("category with name {string}")
    public void categoryWithName(String categoryName) {
        proposal.setCategory(category);
    }

    @And("keywords {string}")
    public void keywords(String keywords) throws Exception{
        proposal.setKeywords(Arrays.asList(keywords.split(", ")));
    }

    @Then("the proposal should be created successfully")
    public void theProposalShouldBeCreatedSuccessfully() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/proposals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(proposal))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(proposal.getTitle())))
                .andExpect(jsonPath("$.description", is(proposal.getDescription())));


        stepDefs.result.andExpect(status().isCreated());

        String title = stepDefs.result.andReturn().getResponse().getHeader("title");
        assertNotNull("Title should not be null", title);
    }

    @Then("the system should throw a constraint error")
    public void theSystemShouldThrowAConstraintError() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/proposals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(proposal))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @And("the proposal should not be created")
    public void theProposalShouldNotBeCreated() throws Exception {
        result.andExpect(status().is4xxClientError());
    }
}