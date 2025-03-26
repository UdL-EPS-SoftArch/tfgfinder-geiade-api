package cat.udl.eps.softarch.tfgfinder.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cat.udl.eps.softarch.tfgfinder.domain.Category;
import cat.udl.eps.softarch.tfgfinder.repository.CategoryRepository;

import java.util.Optional;

@SpringBootTest
public class CreateCategoryStepDefs {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;
    private Optional<Category> retrievedCategory;

    @Given("a category with name {string} and description {string}")
    public void a_category_with_name_and_description(String name, String description) {
        category = new Category();
        category.setName(name);
        category.setDescription(description);
    }

    @When("I save the category")
    public void i_save_the_category() {
        category = categoryRepository.save(category);
    }

    @When("I retrieve the category by name")
    public void i_retrieve_the_category_by_name() {
        retrievedCategory = categoryRepository.findByName(category.getName());
    }

    @Then("the category should exist with name {string} and description {string}")
    public void the_category_should_exist_with_name_and_description(String name, String description) {
        Assertions.assertTrue(retrievedCategory.isPresent(), "Category should be found");
        Assertions.assertEquals(name, retrievedCategory.get().getName(), "Category name should match");
        Assertions.assertEquals(description, retrievedCategory.get().getDescription(), "Category description should match");
    }

    @Then("the system should throw a constraint error")
    public void the_system_should_throw_a_constraint_error() {
        try {
            // Simulate saving the proposal (this should throw an exception if validation fails)
            categoryRepository.save(category);
            Assert.fail("Expected ConstraintViolationException was not thrown.");
        } catch (ConstraintViolationException ignored) {


        }
    }

    @Then("the proposal should not be created")
    public void the_proposal_should_not_be_created() {
        Assertions.assertNull(category.getId());  // Proposal should not be saved
    }
}