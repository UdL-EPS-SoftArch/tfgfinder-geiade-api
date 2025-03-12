package cat.udl.eps.softarch.tfgfinder.steps;

import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.domain.Proposal;
import cat.udl.eps.softarch.tfgfinder.repository.ProposalRepository;
import cat.udl.eps.softarch.tfgfinder.repository.CategoryRepository;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class RegisterProposalStepsDefs {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Proposal proposal;

    @Given("I have a valid proposal with the following details:")
    public void i_have_a_valid_proposal_with_the_following_details(Map<String, String> proposalData) {

        proposal = new Proposal();
        proposal.setTitle(proposalData.get("title"));
        proposal.setDescription(proposalData.get("description"));
        proposal.setTiming(proposalData.get("timing"));
        proposal.setSpeciality(proposalData.get("speciality"));
        proposal.setKind(proposalData.get("kind"));

        // Retrieve or create the category
        String categoryName = proposalData.get("category");
        String categoryDescription = proposalData.get("category_description");
        System.out.println(categoryName);

        Category category = categoryRepository.findCategoryByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    newCategory.setDescription(categoryDescription);
                    return categoryRepository.save(newCategory);
                });

        proposal.setCategory(category);
    }

    @When("I save the proposal")
    public void i_save_the_proposal() {
        proposal = proposalRepository.save(proposal);
    }

    @Then("The proposal should be saved successfully")
    public void the_proposal_should_be_saved_successfully() {
        Assertions.assertNotNull(proposal.getId(), "Proposal was not saved successfully.");
    }

    @Then("I can retrieve the proposal by its ID")
    public void i_can_retrieve_the_proposal_by_its_id() {
        Proposal retrievedProposal = proposalRepository.findById(proposal.getId()).orElse(null);
        Assertions.assertNotNull(retrievedProposal, "Proposal could not be retrieved by its ID.");
    }
}