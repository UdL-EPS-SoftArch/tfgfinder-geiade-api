package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.repository.CategoryRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreateProposalStepDefs {

    @Autowired
    private StepDefs stepDefs;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ProposalRepository proposalRepository;

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

    @And("proposal description {string}")
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
                .andExpect(status().isCreated());

        List<Proposal> proposals = proposalRepository.findByTitle(proposal.getTitle());
        assertTrue("Proposal with title " + proposal.getTitle() + "should exist",
                proposals.stream().anyMatch(p -> p.getTitle().equals(proposal.getTitle())));
    }

    @And("the proposal should not be created")
    public void theProposalShouldNotBeCreated() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/proposals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(proposal))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andExpect(status().isBadRequest());

        List<Proposal> proposals = proposalRepository.findByTitle(proposal.getTitle());
        assertFalse("Proposal with title " + proposal.getTitle() + "shouldn't exist",
                proposals.stream().anyMatch(p -> p.getTitle().equals(proposal.getTitle())));
    }
}
