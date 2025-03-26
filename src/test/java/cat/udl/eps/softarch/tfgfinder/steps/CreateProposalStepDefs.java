package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.domain.User;
import cat.udl.eps.softarch.tfgfinder.repository.CategoryRepository;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.UserRepository;
import io.cucumber.java.en.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class CreateProposalStepDefs {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private User user;
    private Proposal proposal;
    private Category category;

    @Given("a user {string} exists")
    public void aUserExists(String username) {
        user = new User();
        user.setUsername(username);
        user.setEmail(username + "@example.com");
        user.setPassword("securepassword");
        userRepository.save(user);
    }

    @Given("a category {string} with description {string} exists")
    public void aCategoryExists(String categoryName, String categoryDescription) {
        category = new Category();
        category.setName(categoryName);
        category.setDescription(categoryDescription);
        categoryRepository.save(category);
    }

    @When("the user creates a proposal with title {string}")
    public void theUserCreatesAProposalWithTitle(String title) {
        proposal = new Proposal();
        proposal.setTitle(title);
    }

    @When("description {string}")
    public void description(String description) {
        proposal.setDescription(description);
    }

    @When("timing {string}")
    public void timing(String timing) {
        proposal.setTiming(timing);
    }

    @When("speciality {string}")
    public void speciality(String speciality) {
        proposal.setSpeciality(speciality);
    }

    @When("kind {string}")
    public void kind(String kind) {
        proposal.setKind(kind);
    }

    @When("keywords {string}")
    public void keywords(String keywords) {
        proposal.setKeywords(Arrays.asList(keywords.split(", ")));
        proposal.setUser(user);
        proposal.setCategory(category);

    }

    @Then("the proposal should be created successfully")
    public void theProposalShouldBeCreatedSuccessfully() {
        proposalRepository.save(proposal);
        Assertions.assertNotNull(proposal.getId(), "Proposal ID should not be null after saving");

        Optional<Proposal> foundProposal = proposalRepository.findById(proposal.getId());
        Assertions.assertTrue(foundProposal.isPresent(), "Proposal was not created");
    }

    @Then("the system should display an error message {string}")
    public void the_system_should_display_an_error_message(String errorMessage) {
        try {
            // Simulate saving the proposal (this should throw an exception if validation fails)
            proposalRepository.save(proposal);
            Assert.fail("Expected ConstraintViolationException was not thrown.");
        } catch (ConstraintViolationException e) {
            // Check if the exception contains the correct message
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            String error = violations.iterator().next().getMessage();
            Assertions.assertEquals(errorMessage, error);
        }
    }

    @Then("the proposal should not be created")
    public void the_proposal_should_not_be_created() {
        Assertions.assertNull(proposal.getId());  // Proposal should not be saved
    }

}
